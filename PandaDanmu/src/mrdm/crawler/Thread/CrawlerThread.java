package mrdm.crawler.Thread;

import mrdm.crawler.bean.Danmu;
import mrdm.crawler.bean.ServerAddr;
import mrdm.crawler.handler.MessageHandler;
import mrdm.crawler.handler.ResponseHandler;
import mrdm.crawler.util.HttpUtil;
import mrdm.crawler.util.LogUtil;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Created by Wping on 2017/10/2.
 */
public class CrawlerThread implements Runnable {

    private  String roomId;

    public CrawlerThread(String roomId){
        this.roomId = roomId;
    }
    @Override
    public void run() {
        List<ServerAddr> serverAddr = null;
        String requestInfo = null;
        Socket socket = null;

        String url = "http://api.homer.panda.tv/chatroom/getinfo?" + "roomid=" + roomId;
        String s = HttpUtil.get(url);
//      解析弹幕服务器地址
        serverAddr = ResponseHandler.serverAddrHandle(s);
//      解析发送信息
        requestInfo = ResponseHandler.requestInfoHandle(s);
//      获取一个弹幕服务器地址
        ServerAddr shp = serverAddr.get((int)(Math.random()*serverAddr.size()));
        try {
            socket = new Socket(shp.getHost(),shp.getPort());
//      发送验证信息
            MessageHandler.send(socket,requestInfo);
//       启动心跳包线程
            new Thread(new KeepLiveThread(socket)).start();
//        开始接受弹幕
            LogUtil.i("开始接受弹幕...");
            LogUtil.i("------------------------------");
            MessageHandler.receive(socket,danmuListener);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MessageHandler.OnReceiveListener  danmuListener= new MessageHandler.OnReceiveListener(){

        Danmu danmu;

        @Override
        public void onReceive(String response){

            LogUtil.d(response);
//          弹幕类型开头为"type":1，禁言信息为"type"：100末尾包含有"type"：1需要剔除
            if(!response.contains("\"type\":\"1\"")||response.contains("\"type\":\"100\""))
                return;
            try {
                danmu = ResponseHandler.danmuHandle(response);
            }catch (Exception e){
                LogUtil.e(response);
            }
            LogUtil.i(danmu.toString());
        }

    };

}

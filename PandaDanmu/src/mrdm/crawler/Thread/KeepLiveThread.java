package mrdm.crawler.Thread;

import mrdm.crawler.handler.MessageHandler;
import mrdm.crawler.util.LogUtil;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Wping on 2017/10/10.
 */
public class KeepLiveThread implements Runnable {
    Socket socket;
    public KeepLiveThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        LogUtil.i("心跳包启动...");

        while (socket != null && socket.isConnected()) {
            try {
                Thread.sleep(60000);
                MessageHandler.send(socket);
                LogUtil.i("Keep Live ...");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

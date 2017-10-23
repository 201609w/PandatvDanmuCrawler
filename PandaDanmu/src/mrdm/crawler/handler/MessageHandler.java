package mrdm.crawler.handler;


import mrdm.crawler.bean.Message;
import mrdm.crawler.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;


public class MessageHandler {

    /**
     * 发送消息
     */
    public static void send(Socket socket, String content) throws IOException {
        if (socket == null || !socket.isConnected()) return;

        Message message = new Message(content);
        OutputStream out = socket.getOutputStream();
        out.write(message.getBytes());
        LogUtil.i("发送验证信息！");


    }
    public static void send(Socket socket) throws IOException {
        if (socket == null || !socket.isConnected()) return;

        Message message = new Message();
        OutputStream out = socket.getOutputStream();
        out.write(message.getBytes());
        LogUtil.i("发送心跳包！");

    }

    /**
     * 接收消息并处理
     */
    public static void receive(Socket socket,OnReceiveListener listener)
            throws IOException {
        if (socket == null || !socket.isConnected()) {
            LogUtil.e("socket连接失败！");
             return;
        }

        int len;
        byte[] buffer = new byte[10*1024];
        InputStream in = socket.getInputStream();

        while (socket.isConnected() //链接结束
                && (len = in.read(buffer)) != -1 //输入流结束
                ) {
               String s = new String(Arrays.copyOf(buffer, len));
               listener.onReceive(s);

        }
        LogUtil.i("socket连接结束！");
    }

    public interface OnReceiveListener {

        /**
         * 返回数据处理
         */
        void onReceive(String response);

    }





    }

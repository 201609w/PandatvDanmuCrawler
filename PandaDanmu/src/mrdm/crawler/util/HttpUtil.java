package mrdm.crawler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Wping on 2017/10/1.
 */
public class HttpUtil {
    /**
     * GET方法
     */
    public static String get(String url) {

        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            InputStream in = conn.getInputStream();


            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            reader.close();
            in.close();

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

    public static void main(String[] args){
        String url = "http://api.homer.panda.tv/chatroom/getinfo?" + "roomid=" + "1109691";
        String s = HttpUtil.get(url);
        System.out.println(s);
    }
}

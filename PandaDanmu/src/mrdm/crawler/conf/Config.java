package mrdm.crawler.conf;

import mrdm.crawler.util.LogUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Wping on 2017/10/13.
 */
public class Config {
     public static final String propertiesName = "conf.properties";
     public static boolean DEBUG_MODE = true;
     public static boolean loadSuccess = false;
     public static boolean DB_ENABLE = false;
     public static String DB_DIRVER = null;
     public static String DB_URL = null;
     public static String DB_USERNAME = null;
     public static String DB_PASSWORD = null;
     public static Map<String,String> ROOM_MAP = new HashMap<>();

     static{
         InputStream in = null;
         try{
             Properties properties = new Properties();
             in = new FileInputStream(new File(propertiesName));
             properties.load(in);

             DEBUG_MODE = Boolean.parseBoolean(properties.getProperty("debug"));
             DB_ENABLE =  Boolean.parseBoolean(properties.getProperty("db.enable"));
             DB_DIRVER = properties.getProperty("db.dirver");
             DB_URL = properties.getProperty("db.url");
             DB_USERNAME = properties.getProperty("db.username");
             DB_PASSWORD = properties.getProperty("db.password");

             Set<Object> objects = properties.keySet();
             for (Object object:objects){
               String key = ((String)object).trim();
               if(!key.startsWith("[roomName]")) continue;
               ROOM_MAP.put(key.substring(10),properties.getProperty(key));
             }
             loadSuccess = true;
             LogUtil.i("读取配置信息成功！！");
             showConfig();
         } catch (Exception e) {
             e.printStackTrace();
             LogUtil.i("读取配置信息失败！！");
         }finally {
             if(in!=null)
                 try {
                     in.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
         }
     }
     public static void showConfig(){
      LogUtil.d("---------------------配置信息--------------------");
      LogUtil.d("debug: "+ DEBUG_MODE);
      Set<String> keys = ROOM_MAP.keySet();
      for (String key:keys){
          LogUtil.d("主播：" +key+" 房间号："+ROOM_MAP.get(key));
      }
         LogUtil.d("-------------------------------------------------");
     }
     public static void main(String[] args){

     }
}

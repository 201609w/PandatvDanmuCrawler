package mrdm.crawler.util;

import mrdm.crawler.conf.Config;

/**
 * Created by Wping on 2017/10/13.
 */
public class LogUtil {

    private static final String LEVEL_INFO = "INFO";
    private static final String LEVEL_ERROR ="ERROR";

    public static String timestamp(){return DateUtil.now();}
    public static void printOut(String log){System.out.println(log);}

    public static String logMessage(String level,String message){
        return String.format("[%s][%s][%s]%s",timestamp(),level,Thread.currentThread().getName(),message);
    }

    public static void i(String message){
        printOut(logMessage(LEVEL_INFO,message));
    }

    public static void d(String message){
        if(Config.DEBUG_MODE){
            printOut(logMessage(LEVEL_INFO,message));
        }
    }

    public static void e(String message){
        printOut(logMessage(LEVEL_ERROR,message));
    }


    public static void main(String[] args){
        i("测试！！");
    }
}

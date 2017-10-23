package mrdm.crawler.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Wping on 2017/10/13.
 */
public class DateUtil {
//编辑时间格式
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

// 返回日期+时间字符串
    public static String now(){return formatter.format(new Date()); }

//  返回日期+时间字符串
    public static String datatime(Date date){return formatter.format(date);}


    public static void main(String[] args){

        LogUtil.i(now());

    }
}

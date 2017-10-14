package mrdm.crawler.handler;

import mrdm.crawler.bean.Danmu;
import mrdm.crawler.bean.ServerAddr;
import mrdm.crawler.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wping on 2017/10/1.
 */

public class ResponseHandler {


    private static final String DANMU_SERVER = "\"(.*?):(.*?)\"";
    private static final String APPID_REGEX = "appid\":\"(.*?)\"";
    private static final String RID_REGEX ="rid\":(.*?),\"";
    private static final String SIGN_REGEX ="sign\":\"(.*?)\"";
    private static final String AUTHTYPE_REGEX ="authType\":\"(.*?)\"";
    private static final String TS_REGEX ="ts\":(.*?),\"";
    private static final String DANMU_NICKNAME = "\"nickName\":\"(.*?)\"";
    private static final String DANMU_USERLEVEL ="\"level\":\"(.*?)\"";
    private static final String DANMU_CONTENT ="\"content\":\"(.*?)\"" ;


    public static Matcher getMatcher(String content, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        return pattern.matcher(content);
    }

    public static String getMatcherString(String content,String regex){
        Matcher m = getMatcher(content,regex);
        m.find();
        return m.group(1);
    }
/*
* 解析弹幕地址
*/
    public static List<ServerAddr> serverAddrHandle(String content){

        List<ServerAddr> serverAddrList = new ArrayList<ServerAddr>();
        content = content.split("\"chat_addr_list\":")[1];
        Matcher m = getMatcher(content,DANMU_SERVER);

        while (m.find()){
            ServerAddr serverAddr = new ServerAddr(m.group(1),Integer.parseInt(m.group(2)));
            serverAddrList.add(serverAddr);
        }
        return serverAddrList;
    }
/*
* 解析需要发送的相关信息
*/

    public static String requestInfoHandle(String content){
        String rid;
        String appid;
        String k = "1";
        String t = "30";
        String ts;
        String sign;
        String authType;

        rid = getMatcherString(content,RID_REGEX);
        appid =getMatcherString(content,APPID_REGEX);
        ts = getMatcherString(content,TS_REGEX);
        sign = getMatcherString(content,SIGN_REGEX);
        authType =getMatcherString(content,AUTHTYPE_REGEX);
        return "u:" + rid + "@" + appid + "\n" +
                "k:" + k + "\n" +
                "t:" + t + "\n" +
                "ts:" + ts + "\n" +
                "sign:" + sign + "\n" +
                "authtype:" + authType;
    }

/*
*解析弹幕
*/
    public static Danmu danmuHandle(String content){
        String nickName;
        int level;
        String danMuContent;

        nickName = getMatcherString(content,DANMU_NICKNAME);
        level = Integer.parseInt(getMatcherString(content,DANMU_USERLEVEL));
        danMuContent = getMatcherString(content,DANMU_CONTENT);

        return new Danmu(nickName,level,danMuContent);


    }



    public static void main(String[] args){
//        String content ="{\"errno\":0,\"errmsg\":\"\",\"data\":{\"appid\":\"135327419\",\"rid\":-17218051,\"sign\":\"3bdc52b4a150c2b7a8288c66c458c667\",\"authType\":\"4\",\"ts\":1506843549000,\"chat_addr_list\":[\"115.159.247.235:8080\",\"118.89.11.35:8080\",\"118.89.11.35:443\",\"118.89.11.24:443\",\"118.89.11.24:8080\",\"115.159.247.235:443\"]}}";
          String danmucontent = "{\"type\":\"1\",\"time\":1507906718,\"data\":{\"from\":{\"identity\":\"30\",\"nickName\":\"永远无罪\",\"badge\":\"\",\"rid\":\"27579524\",\"sp_identity\":\"30\",\"msgcolor\":\"\",\"level\":\"7\",\"ispay\":1,\"__plat\":\"pc_web\",\"userName\":\"\"},\"to\":{\"toroom\":\"10015\"},\"content\":\"这彩蛋\"}}";
//        List<ServerAddr> serverAddrList = ResponseHandler.serverAddrHandle(content);
//        System.out.println(serverAddrList.get((int)Math.random()*serverAddrList.size()));
//        System.out.println(ResponseHandler.requestInfoHandle(content));
          Danmu danmu = danmuHandle(danmucontent);
           LogUtil.i(danmu.toString());
    }

}

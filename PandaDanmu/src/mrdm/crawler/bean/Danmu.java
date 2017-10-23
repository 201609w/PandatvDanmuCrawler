package mrdm.crawler.bean;

import mrdm.crawler.util.DateUtil;

import java.util.Date;

/**
 * Created by Wping on 2017/10/11.
 */
public class Danmu {
    private String nickName;
    private int uid;
    private int level;
    private String content;
    private int  roomid;
    private String date;


    public Danmu(String nickName, int uid, int level, String content, int roomid){
        this.nickName = nickName;
        this.level = level;
        this.content = content;
        this.uid = uid;
        this.roomid =roomid;

        this.date = DateUtil.now();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getLevel() {
        return level;
    }

    public int getUid() {
        return uid;
    }

    public String getDate() {
        return date;
    }

    public int getRoomid() {
        return roomid;
    }

    @Override
    public String toString() {
        return "[level "+level+"]"+nickName +": "+content;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package mrdm.crawler.bean;

/**
 * Created by Wping on 2017/10/11.
 */
public class Danmu {
    private String nickName;
    private int level;
    private String content;

    public Danmu(String nickName,int level,String content){
        this.nickName = nickName;
        this.level = level;
        this.content = content;
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

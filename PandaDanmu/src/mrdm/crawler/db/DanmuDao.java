package mrdm.crawler.db;

import mrdm.crawler.bean.Danmu;
import mrdm.crawler.util.DBUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wping on 2017/10/18.
 */
public class DanmuDao {
    private static final String SQL_INSERT_DANMAKU = "INSERT INTO danmus(uid,nickname,level,content, date,roomid) VALUES(%d,'%s',%d,'%s','%s',%d); ";

    /**
     * 保存弹幕数据到数据库
     */
    public static boolean saveDanmu(List<Danmu> danmuList) {
        List<String> sqlList = new ArrayList<>();
        for (Danmu danmu : danmuList) {
            sqlList.add(String.format(SQL_INSERT_DANMAKU,danmu.getUid(),
               danmu.getNickName(),danmu.getLevel(),danmu.getContent(),
                    danmu.getDate(),danmu.getRoomid())
            );
        }
        return DBUtil.execSQL(sqlList);
    }

    public static void main(String[] args) {
        //测试
        List<Danmu> danmus = new ArrayList<Danmu>();
        Danmu danmu = new Danmu("admin",10000,7,"test",8888888);
        danmus.add(danmu);
        saveDanmu(danmus);
    }
}

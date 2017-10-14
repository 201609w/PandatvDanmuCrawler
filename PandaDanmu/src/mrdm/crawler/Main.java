package mrdm.crawler;

import mrdm.crawler.Thread.CrawlerThread;
import mrdm.crawler.conf.Config;
import java.util.Set;



/**
 * Created by Wping on 2017/10/2.
 */
public class Main {

    public static void main(String[] args){
        if(!Config.loadSuccess) return;

        Set<String> nameSet = Config.ROOM_MAP.keySet();

        for(String name:nameSet){
            new Thread(new CrawlerThread(Config.ROOM_MAP.get(name)),name).start();
        }

    }
}

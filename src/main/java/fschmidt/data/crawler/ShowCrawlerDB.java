package fschmidt.data.crawler;

import fschmidt.data.crawler.db.EmbeddedDerbyDB;

/**
 *
 * @author fschmidt
 */
public class ShowCrawlerDB {
    public static void main(String[] args){
        //Handling args parameters
        if ((args == null) || args.length != 1) {
            System.err.println("Parameters: <database path>");
            System.exit(0);
        }
        String path = args[0];
//        String path = "/Volumes/sd/crawler-news-db/data/";
        EmbeddedDerbyDB.getInstance(path).getAllEnglishTexts();
    }
}

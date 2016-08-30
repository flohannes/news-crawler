package fschmidt.data.crawler;

import fschmidt.data.crawler.db.EmbeddedDerbyDB;

/**
 *
 * @author fschmidt
 */
public class ShowCrawlerDB {
    public static void main(String[] args){
        String path = "/Volumes/sd/crawler-news-db/data/";
        EmbeddedDerbyDB.getInstance(path).getAllEnglishTexts();
    }
}

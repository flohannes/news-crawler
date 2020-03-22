package fschmidt.data.crawler;

import fschmidt.data.crawler.db.EmbeddedDerbyDB;
import fschmidt.data.crawler.input.rss.RssUrl;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author fschmidt
 */
public class Run {

    public static void main(String[] args) {
        //Handling args parameters
        if ((args == null) || args.length != 2) {
            System.err.println("Parameters: <database path> <crawling interval in min:double>");
            System.exit(0);
        }
        String dbPath = args[0];
        double meanCrawlerInterval = Double.valueOf(args[1]);

        //exist/create database
//        File dbFilePath = new File(dbPath);
//        dbFilePath.mkdirs();
//        EmbeddedDerbyDB.getInstance(dbPath).createDatabase();

        //Run crawling job
        List<RssUrl> rssFeeds = Arrays.asList(RssUrl.values());
        new CrawlService(rssFeeds, dbPath, meanCrawlerInterval).run();
    }
}

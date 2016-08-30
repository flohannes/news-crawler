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
        if ((args == null) || args.length != 3) {
            System.err.println("Parameters: <database path> <crawle interval in min:double> <randomize interval:true/false>");
            System.exit(0);
        }
        String dbPath = args[0];
        double meanCrawlerInterval = Double.valueOf(args[1]);
        boolean randomize = Boolean.getBoolean(args[2]);

        //exist/create database
        File dbFilePath = new File(dbPath);
        dbFilePath.mkdirs();
        EmbeddedDerbyDB.getInstance(dbPath).createDatabase();

        //Run crawling job
        List<RssUrl> rssFeeds = Arrays.asList(RssUrl.values());
        new CrawlService(rssFeeds, dbPath, meanCrawlerInterval, randomize).run();
    }
}

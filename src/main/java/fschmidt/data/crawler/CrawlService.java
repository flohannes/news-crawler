package fschmidt.data.crawler;

import fschmidt.data.crawler.db.ElasticSearch;
import fschmidt.data.crawler.input.rss.RssStreamSource;
import fschmidt.data.crawler.input.rss.RssUrl;
import fschmidt.data.crawler.model.Text;
import java.util.*;
import java.util.List;

/**
 * This class orchestrates the crawling of different feeds. It starts in a randomly picked time interval a randomly choosen crawler.
 * @author fschmidt
 */
public class CrawlService extends TimerTask {

    private final Timer timer = new Timer();
    private final List<RssUrl> rssFeeds;
    private final String databasePath;
    private final double crawleInterval;
    private final String imagePath;

    /**
     * 
     * @param rssFeeds
     * @param path
     * @param crawleInterval
     * @param imagePath
     */
    public CrawlService(List<RssUrl> rssFeeds, String path, double crawleInterval, String imagePath) {
        this.rssFeeds = rssFeeds;
        this.databasePath = path;
        this.crawleInterval = crawleInterval;
        this.imagePath = imagePath;
    }

    /**
     * This method chooses randomly a feed, crawles the feed and tries to store new texts into the database.
     * @param rssFeeds All feeds, from which one feed can be randomly picked for crawling.
     */
    public void runCrawler(List<RssUrl> rssFeeds) {
        for(RssUrl rssUrl : rssFeeds){
            RssStreamSource rssStreamSource = new RssStreamSource(rssUrl);
            List<Text> texts = rssStreamSource.runCrawler();
            for(Text text : texts){
                ElasticSearch.getInstance(databasePath).addText(text);
            }
        }
    }
    
    @Override
    public void run() {
        int delay = 60000;
        delay *= crawleInterval;
        timer.schedule(new CrawlService(rssFeeds, databasePath, crawleInterval, imagePath), delay);
        runCrawler(rssFeeds);
    }
}

package fschmidt.data.crawler;

import fschmidt.data.crawler.db.EmbeddedDerbyDB;
import fschmidt.data.crawler.input.rss.RssStreamSource;
import fschmidt.data.crawler.input.rss.RssUrl;
import fschmidt.data.crawler.model.Text;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class orchestrates the crawling of different feeds. It starts in a randomly picked time interval a randomly choosen crawler.
 * @author fschmidt
 */
public class CrawlService extends TimerTask {

    private final Timer timer = new Timer();
    private final List<RssUrl> rssFeeds;
    private final String databasePath;
    private final double crawleInterval;
    private final boolean randomize;

    /**
     * 
     * @param rssFeeds
     * @param path
     * @param crawleInterval
     * @param randomize 
     */
    public CrawlService(List<RssUrl> rssFeeds, String path, double crawleInterval, boolean randomize) {
        this.rssFeeds = rssFeeds;
        this.databasePath = path;
        this.crawleInterval = crawleInterval;
        this.randomize = randomize;
    }

    /**
     * This method chooses randomly a feed, crawles the feed and tries to store new texts into the database.
     * @param rssFeeds All feeds, from which one feed can be randomly picked for crawling.
     */
    public void runCrawler(List<RssUrl> rssFeeds) {
        Random random = new Random();
        int index = random.nextInt(rssFeeds.size());
        //Randomly pick a feed
        RssUrl rssUrl = rssFeeds.get(index);
        System.out.println(rssUrl.name());
        RssStreamSource rssStreamSource = new RssStreamSource(rssUrl);
        List<Text> texts = rssStreamSource.runCrawler();
        //Add texts to DB
        for(Text text : texts){
            EmbeddedDerbyDB.getInstance(databasePath).addText(text);
        }
        System.out.println(rssUrl.name() + ": " + texts);
    }
    
    @Override
    public void run() {
        //Calculate a random delay for starting the next crawler.
        int delay = 60000; 
        if(randomize){
            int nextInt = (int) (crawleInterval/2.0);
            if(nextInt <= 0){
                nextInt = 1;
            }
            delay *= (crawleInterval + new Random().nextInt(nextInt));
        } else{
            delay *= crawleInterval;
        }
        timer.schedule(new CrawlService(rssFeeds, databasePath, crawleInterval, randomize), delay);
        runCrawler(rssFeeds);
    }
}

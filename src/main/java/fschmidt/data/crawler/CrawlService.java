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
 *
 * @author fschmidt
 */
public class CrawlService extends TimerTask {

    private final Timer timer = new Timer();
    private final List<RssUrl> rssFeeds;
    private final String databasePath;
    private final double crawleInterval;
    private final boolean randomize;

    public CrawlService(List<RssUrl> rssFeeds, String path, double crawleInterval, boolean randomize) {
        this.rssFeeds = rssFeeds;
        this.databasePath = path;
        this.crawleInterval = crawleInterval;
        this.randomize = randomize;
    }

    public void runCrawler(List<RssUrl> rssFeeds) {
        Random random = new Random();
        int index = random.nextInt(rssFeeds.size());
        //check if source locked
        RssUrl rssUrl = rssFeeds.get(index);
        System.out.println(rssUrl.name());
        RssStreamSource rssStreamSource = new RssStreamSource(rssUrl);
        List<Text> texts = rssStreamSource.runCrawler();
        //Add to DB
        for(Text text : texts){
            EmbeddedDerbyDB.getInstance(databasePath).addText(text);
        }
        System.out.println(rssUrl.name() + ": " + texts);
    }
    
    @Override
    public void run() {
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

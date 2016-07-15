package fschmidt.data.crawler.input.rss;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import fschmidt.data.crawler.model.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class RssStreamSource {

    private final RssUrl rssUrl;

    public RssStreamSource(RssUrl rssUrl) {
        this.rssUrl = rssUrl;
    }

    public List<Text> runCrawler() {
        List<Text> texts = new ArrayList<>();
        SyndFeedInput input = new SyndFeedInput();
        try {
            SyndFeed feed = input.build(new XmlReader(new URL(rssUrl.getSource().getFeedSource())));
            for (SyndEntry entry : feed.getEntries()) {

                String title = entry.getTitle();
                String description = "";
                if (entry.getDescription() != null) {
                    description = entry.getDescription().getValue();
                }
                List<String> categories = new ArrayList<>();
                for (SyndCategory rssCategory : entry.getCategories()) {
                    categories.add(rssCategory.getName());
                }

                String link = entry.getLink();
                String wholeText = rssUrl.getWebsiteText().getWholeText(link);

                //TODO: Check if text exists in DB
                Text text = new Text(entry.getUri(), new Date(), entry.getAuthor(), description, title, wholeText, categories.toArray(
                        new String[categories.size()]), rssUrl.getSource());
                texts.add(text);
//                if (!ElasticSearchConnector.getInstance().isTextAlreadyCrawled(text) && !wholeText.isEmpty()) {
//                    System.out.println("text: " + wholeText);
//
//                    //Save new Text
//                    ElasticSearchConnector.getInstance().addDocument(ElasticSearchConnector.NEWTEXT_DOCUMENTTYPE, text);
//                } else {
//                    System.out.println("text: non");
//                }
            }
        } catch (IllegalArgumentException | FeedException | IOException ex) {
            Logger.getLogger(RssStreamSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return texts;
    }

}

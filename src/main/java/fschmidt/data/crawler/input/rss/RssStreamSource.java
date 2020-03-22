package fschmidt.data.crawler.input.rss;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import fschmidt.data.crawler.model.Text;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class crawles for a given feed all texts in the feed.
 *
 * @author Florian
 */
public class RssStreamSource {

    private final RssUrl rssUrl;

    /**
     * Constructor of RssStreamSource
     *
     * @param rssUrl RssUrl enum value to be used for crawling.
     */
    public RssStreamSource(RssUrl rssUrl) {
        this.rssUrl = rssUrl;
    }

    /**
     * This method iterates through all Entry from a given Rss Feed. For each entry consists of information, which are added to a new Text
     * instance. The original article is crawled in addition from the given Url.
     *
     * @return Retrieved texts from the crawling.
     */
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
                Document doc = rssUrl.getWebsiteText().getDocument(link);
                String wholeText = rssUrl.getWebsiteText().getWholeText(doc);
                String specificText = rssUrl.getWebsiteText().getSpecificText(doc);

                Text text = new Text(entry.getUri(), new Date(), entry.getAuthor(), description, title, wholeText, specificText, categories.toArray(
                        new String[categories.size()]), rssUrl.getSource(), link);
                texts.add(text);
            }
        } catch (IllegalArgumentException | FeedException | IOException ex) {
            Logger.getLogger(RssStreamSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return texts;
    }

}

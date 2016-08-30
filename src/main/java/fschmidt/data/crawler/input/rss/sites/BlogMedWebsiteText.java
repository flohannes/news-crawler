package fschmidt.data.crawler.input.rss.sites;

import fschmidt.data.crawler.input.rss.RetrieveWebsiteText;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Florian
 */
public class BlogMedWebsiteText implements RetrieveWebsiteText {

    @Override
    public String getWholeText(String link) {
        String wholeText = "";
        try {
            // need http protocol
            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
            if (doc.select(".entry-content") != null) {
                if (doc.select(".entry-content").first() != null) {
                    wholeText = doc.select(".entry-content").first().text();
                }
            }
        } catch (IOException e) {
            Logger.getLogger(BlogMedWebsiteText.class.getName()).log(Level.SEVERE, null, e);
        }
        return wholeText;
    }

}

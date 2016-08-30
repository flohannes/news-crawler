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
public class FAZWebsiteText implements RetrieveWebsiteText {

    @Override
    public String getWholeText(String link) {
        String wholeText = "";
        try {
            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
            if (doc.select(".FAZArtikelText") != null) {
                if (doc.select(".FAZArtikelText").first() != null) {
                    wholeText = doc.select(".FAZArtikelText").first().text();
                }
            }
        } catch (IOException e) {
            Logger.getLogger(FAZWebsiteText.class.getName()).log(Level.SEVERE, null, e);
        }
        return wholeText;
    }

}

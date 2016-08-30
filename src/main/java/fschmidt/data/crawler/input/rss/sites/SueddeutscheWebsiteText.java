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
public class SueddeutscheWebsiteText implements RetrieveWebsiteText {

    @Override
    public String getWholeText(String link) {
        String wholeText = "";
        try {
            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
            if (doc.select("#article-body") != null) {
                if (doc.select("#article-body").first() != null) {
                    wholeText = doc.select("#article-body").first().text();
                }
            }
        } catch (IOException e) {
            Logger.getLogger(SueddeutscheWebsiteText.class.getName()).log(Level.SEVERE, null, e);
        }
        return wholeText;
    }

}

package fschmidt.data.crawler.input.rss;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Florian
 */
public class AerzteblattWebsiteText implements RetrieveWebsiteText {

    @Override
    public String getWholeText(String link) {
        String wholeText = "";
        try {
            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
            if (doc.select("#newsContent") != null) {
                if (doc.select("#newsContent").first() != null) {
                    wholeText = doc.select("#newsContent").first().text();
                }
            }
        } catch (IOException e) {
            Logger.getLogger(AerzteblattWebsiteText.class.getName()).log(Level.SEVERE, null, e);
        }
        return wholeText;
    }

}

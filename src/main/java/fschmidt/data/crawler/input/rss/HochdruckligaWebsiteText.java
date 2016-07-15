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
public class HochdruckligaWebsiteText implements RetrieveWebsiteText {

    @Override
    public String getWholeText(String link) {
        String wholeText = "";
        try {
            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
            if (doc.select(".ce_text") != null) {
                if (doc.select(".ce_text").first() != null) {
                    wholeText = doc.select(".ce_text").first().text();
                }
            }
        } catch (IOException e) {
            Logger.getLogger(HochdruckligaWebsiteText.class.getName()).log(Level.SEVERE, null, e);
        }
        return wholeText;
    }

}

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
public class DefaultWebsiteText implements RetrieveWebsiteText {

    @Override
    public String getWholeText(String link) {
        String wholeText = "";
        try {
            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
            wholeText = doc.text();
        } catch (IOException ex) {
//            Logger.getLogger(DefaultWebsiteText.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return wholeText;
    }

}

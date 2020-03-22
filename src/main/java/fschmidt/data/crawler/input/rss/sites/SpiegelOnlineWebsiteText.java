package fschmidt.data.crawler.input.rss.sites;

import fschmidt.data.crawler.input.rss.DefaultWebsiteText;
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
public class SpiegelOnlineWebsiteText extends DefaultWebsiteText {

    @Override
    public String getSpecificText(Document doc) {
        if(doc == null){
            return "";
        }        String wholeText = "";
//        try {
//            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
            if (doc.select(".relative") != null) {
                if (doc.select(".relative").first() != null) {
                    wholeText = doc.select(".relative").first().text();
                }
            }
//        } catch (IOException e) {
//            Logger.getLogger(SpiegelOnlineWebsiteText.class.getName()).log(Level.SEVERE, null, e);
//        }
        return wholeText;
    }

}

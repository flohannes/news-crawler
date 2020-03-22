package fschmidt.data.crawler.input.rss.sites;

import fschmidt.data.crawler.input.rss.DefaultWebsiteText;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Florian
 */
public class SueddeutscheWebsiteText extends DefaultWebsiteText {

    @Override
    public String getSpecificText(Document doc) {
        if(doc == null){
            return "";
        }
        String wholeText = "";
//        try {
//            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
            if (doc.select("#article-app-container") != null) {
                if (doc.select("#article-app-container").first() != null) {
                    wholeText = doc.select("#article-app-container").first().text();
                }
            }
//        } catch (IOException e) {
//            Logger.getLogger(SueddeutscheWebsiteText.class.getName()).log(Level.SEVERE, null, e);
//        }
        return wholeText;
    }

}

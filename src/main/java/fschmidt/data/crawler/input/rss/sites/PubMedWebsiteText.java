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
public class PubMedWebsiteText extends DefaultWebsiteText {

    @Override
    public String getSpecificText(Document doc) {
        if(doc == null){
            return "";
        }        String wholeText = "";
//        try {
            // need http protocol
//            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
            if (doc.select(".snews-article__article-body--full-text") != null) {
                if (doc.select(".snews-article__article-body--full-text").first() != null) {
                    wholeText = doc.select(".snews-article__article-body--full-text").first().text();
                }
            }
//        } catch (IOException e) {
//            Logger.getLogger(ZeitWebsiteText.class.getName()).log(Level.SEVERE, null, e);
//        }
        return wholeText;
    }

}

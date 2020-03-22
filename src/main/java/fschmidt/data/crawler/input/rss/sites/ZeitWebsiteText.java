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
public class ZeitWebsiteText extends DefaultWebsiteText {

    @Override
    public String getSpecificText(Document doc) {
        if(doc == null){
            return "";
        }
        String wholeText = "";
//        try {
            // need http protocol
//            String newUrl = "";
//            if (link.startsWith("http://")) {
//                newUrl = link;
//            } else if (link.startsWith("www")) {
//                newUrl = "http://" + link;
//            } else if(link.startsWith("/")){
//                return "";
//            }

//            Document doc = Jsoup.connect(newUrl).timeout(10 * 1000).get();
            if (doc.select(".article-page") != null) {
                if (doc.select(".article-page").first() != null) {
                    wholeText = doc.select(".article-page").first().text();
                }
            }
//        } catch (IOException e) {
//            Logger.getLogger(ZeitWebsiteText.class.getName()).log(Level.SEVERE, null, e);
//        }
        return wholeText;
    }

}

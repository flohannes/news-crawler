package fschmidt.data.crawler.input.rss.sites;

import fschmidt.data.crawler.input.rss.DefaultWebsiteText;
import org.jsoup.nodes.Document;

public class WeltWebsiteText extends DefaultWebsiteText {

    @Override
    public String getSpecificText(Document doc) {
        if(doc == null){
            return "";
        }
        String wholeText = "";
//        try {
//            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
        if (doc.select(".c-sticky-container") != null) {
            if (doc.select(".c-sticky-container").first() != null) {
                wholeText = doc.select(".c-sticky-container").first().text();
            }
        }
//        } catch (IOException e) {
//            Logger.getLogger(SueddeutscheWebsiteText.class.getName()).log(Level.SEVERE, null, e);
//        }
        return wholeText;
    }
//.c-sticky-container
}

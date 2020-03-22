package fschmidt.data.crawler.input.rss.sites;

import fschmidt.data.crawler.input.rss.DefaultWebsiteText;
import org.jsoup.nodes.Document;

public class SternWebsiteText extends DefaultWebsiteText {

    @Override
    public String getSpecificText(Document doc) {
        if(doc == null){
            return "";
        }
        String wholeText = "";
//        try {
//            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
        if (doc.select(".article-content") != null) {
            if (doc.select(".article-content").first() != null) {
                wholeText = doc.select(".article-content").first().text();
            }
        }
//        } catch (IOException e) {
//            Logger.getLogger(SueddeutscheWebsiteText.class.getName()).log(Level.SEVERE, null, e);
//        }
        return wholeText;
    }
//    .article-content
}

package fschmidt.data.crawler.input.rss;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * This class implements a simple routine to retrieve the text of a given website.
 * @author Florian
 */
public class DefaultWebsiteText implements RetrieveWebsiteText {

    @Override
    public String getWholeText(Document doc) {
        if(doc == null){
            return "";
        }
        String wholeText = doc.text();
        return wholeText;
    }

    public String getSpecificText(Document doc){
        return "";
    }

    public Document getDocument(String link){
        Document doc = null;
        try {
            doc = Jsoup.connect(link).timeout(10 * 1000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

}

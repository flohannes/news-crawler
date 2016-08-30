package fschmidt.data.crawler.input.rss.sites;

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
public class ReutersWebsiteText implements RetrieveWebsiteText {

    @Override
    public String getWholeText(String link) {
        String wholeText = "";
        try {
            // need http protocol
            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
            if (doc.select("#articleText") != null) {
                if (doc.select("#articleText").first() != null) {
                    wholeText = doc.select("#articleText").first().text();

                    if (wholeText.contains("(Reporting by ")) {
                        wholeText = wholeText.substring(0, wholeText.length() - (wholeText.length() - wholeText.indexOf("(Reporting by ")));
                    }
                    if (wholeText.contains("(Reporting By ")) {
                        wholeText = wholeText.substring(0, wholeText.length() - (wholeText.length() - wholeText.indexOf("(Reporting By ")));
                    }
                }
            }
        } catch (IOException e) {
            Logger.getLogger(ZeitWebsiteText.class.getName()).log(Level.SEVERE, null, e);
        }
        return wholeText;
    }

}

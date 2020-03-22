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
public class BBCWebsiteText extends DefaultWebsiteText {

    @Override
    public String getSpecificText(Document doc) {
        if(doc == null){
            return "";
        }        String wholeText = "";
//        try {
//            // need http protocol
//            Document doc = Jsoup.connect(link).timeout(10 * 1000).get();
            if (doc.select(".story-body__inner") != null) {
                if (doc.select(".story-body__inner").first() != null) {

                    wholeText = doc.select(".story-body__inner").first().text();
                    if (wholeText.startsWith("Image caption")) {
                        wholeText = wholeText.substring(14, wholeText.length());
                    }
                    if (wholeText.endsWith("Have you been affected by this glitch? You can email haveyoursay@bbc.co.uk with your story. Please include a contact number if you are willing to speak to a BBC journalist. You can also contact us in the following ways: Whatsapp: +44 7525 900971 Tweet: @BBC_HaveYourSay Send an SMS or MMS to 61124 or +44 7624 800 100")) {
                        wholeText = wholeText.substring(0, wholeText.length() - 317);
                    }
                    if (wholeText.endsWith("Have you been affected by this story? If you wish to contact BBC News, you can do so by emailing haveyoursay@bbc.co.uk Please include a contact number if you are willing to speak to a BBC journalist. You can also contact us in the following ways: Whatsapp: +44 7525 900971 Send pictures/video to yourpics@bbc.co.uk Or Upload your pictures/video here Tweet: @BBC_HaveYourSay Send an SMS or MMS to 61124 or +44 7624 800 100")) {
                        wholeText = wholeText.substring(0, wholeText.length() - 421);
                    }

                }
            }
//        } catch (IOException e) {
//            Logger.getLogger(ZeitWebsiteText.class.getName()).log(Level.SEVERE, null, e);
//        }
        return wholeText;
    }
    
}

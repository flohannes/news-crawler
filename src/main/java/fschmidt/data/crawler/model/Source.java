package fschmidt.data.crawler.model;

import java.util.Locale;

/**
 *
 * @author fschmidt
 */
public class Source {
    private final String feedSource;
    private final Locale language;
    
    public Source(String feedSource, Locale language) {
        this.feedSource = feedSource;
        this.language = language;
    }

    public String getFeedSource() {
        return feedSource;
    }

    public Locale getLanguage() {
        return language;
    }
}

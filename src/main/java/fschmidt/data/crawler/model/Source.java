package fschmidt.data.crawler.model;

import java.util.Locale;

/**
 * This class represents a feed source. It consists of the rss feed url and the language, which is used of the feed author.
 * @author fschmidt
 */
public class Source {
    private final String feedSource;
    private final Locale language;
    
    /**
     * Constructor for Source.
     * @param feedSource Url of the Rss Feed
     * @param language Language of articles on the RSS Feed
     */
    public Source(String feedSource, Locale language) {
        this.feedSource = feedSource;
        this.language = language;
    }

    /**
     * 
     * @return 
     */
    public String getFeedSource() {
        return feedSource;
    }

    /**
     * 
     * @return 
     */
    public Locale getLanguage() {
        return language;
    }
}

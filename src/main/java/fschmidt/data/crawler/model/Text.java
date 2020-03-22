package fschmidt.data.crawler.model;

import java.util.Date;

/**
 * This class represents a Text with all its properties. It is based on the structure of Rss Feeds.
 * @author fschmidt
 */
public class Text {

    private final String article;
    private final String textSourceUrl;
    private final Date timestamp;
    private final String author;
    private final String description;
    private final String title;
    private final String link;
    private final String specificText;
    private final String[] tags;
    private final Source source;

    /**
     * Constructor of a Text entry.
     * @param textSourceUrl Url of specific Rss Entry to be crawled.
     * @param timestamp Timestamp of crawled text.
     * @param author Author of the text.
     * @param description Given description in the Rss Entry for the text.
     * @param title Title of the Rss Entry article.
     * @param article The original text itself.
     * @param tags Tags, which were set in the Rss Entry.
     * @param source Rss Feed source with its Url and language.
     */
    public Text(String textSourceUrl, Date timestamp, String author, String description, String title, String article, String specificText, String[] tags,
            Source source, String link) {
        this.textSourceUrl = textSourceUrl;
        this.timestamp = timestamp;
        this.author = author;
        this.description = description;
        this.title = title;
        this.article = article;
        this.tags = tags;
        this.source = source;
        this.link = link;
        this.specificText = specificText;
    }

    /**
     * 
     * @return Url of Rss Entry
     */
    public String getTextSourceUrl() {
        return textSourceUrl;
    }

    /**
     * 
     * @return Cralwing timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @return Author of the Rss Entry
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @return Description of the crawled article
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @return Title of the text
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @return The original text itself
     */
    public String getArticle() {
        return article;
    }

    /**
     * 
     * @return Tags created by the author
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * 
     * @return Rss Feed Url and language
     */
    public Source getSource() {
        return source;
    }

    public String getLink() {
        return link;
    }

    public String getSpecificText() {
        return specificText;
    }

    @Override
    public String toString() {
        return this.title;
    }
}

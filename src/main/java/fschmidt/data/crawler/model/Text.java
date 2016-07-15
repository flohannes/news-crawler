package fschmidt.data.crawler.model;

import java.util.Date;

/**
 *
 * @author fschmidt
 */
public class Text {

    private final String article;
    private final String textSourceUrl;
    private final Date timestamp;
    private final String author;
    private final String description;
    private final String title;
    private final String[] tags;
    private final Source source;

    public Text(String textSourceUrl, Date timestamp, String author, String description, String title, String article, String[] tags,
            Source source) {
        this.textSourceUrl = textSourceUrl;
        this.timestamp = timestamp;
        this.author = author;
        this.description = description;
        this.title = title;
        this.article = article;
        this.tags = tags;
        this.source = source;
    }

    public String getTextSourceUrl() {
        return textSourceUrl;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getArticle() {
        return article;
    }

    public String[] getTags() {
        return tags;
    }

    public Source getSource() {
        return source;
    }

    @Override
    public String toString() {
        return this.title;
    }
}

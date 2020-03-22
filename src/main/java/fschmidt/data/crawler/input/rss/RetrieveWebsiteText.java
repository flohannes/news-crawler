/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fschmidt.data.crawler.input.rss;

import org.jsoup.nodes.Document;

/**
 * This interface is an adapter to website specific text crawling extensions.
 * @author Florian
 */
public interface RetrieveWebsiteText {
    /**
     * 
     * @param doc Url to the website to be crawled.
     * @return Extracted text from the website.
     */
    public String getWholeText(Document doc);
    public String getSpecificText(Document doc);
    public Document getDocument(String link);
}

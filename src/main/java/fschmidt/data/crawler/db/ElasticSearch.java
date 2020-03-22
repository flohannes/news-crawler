package fschmidt.data.crawler.db;

import fschmidt.data.crawler.model.Source;
import fschmidt.data.crawler.model.Text;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ElasticSearch implements DatabaseConnection{

    private static ElasticSearch instance;
    private String database = "CrawlerDB";
    private RestHighLevelClient client;

    private ElasticSearch(String path) {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(path, 9200, "http")//,
                ));
    }

    /**
     * Gets an instance of this class, conntected to a given embedded derby database at the given path.
     *
     * @param path The directory path to the embedded derby database.
     * @return
     */
    public static ElasticSearch getInstance(String path) {
        if (instance == null) {
            instance = new ElasticSearch(path);
        }
        return instance;
    }

    @Override
    public void createDatabase() {
    }

    @Override
    public void addText(Text text) {
        //check beforehand whether text already exists
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("title", text.getTitle()));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(searchResponse.getHits().getHits().length > 0){
            return;
        }

        IndexRequest request = new IndexRequest("posts");

        XContentBuilder builder = null;
        try {
            builder = XContentFactory
                    .jsonBuilder()
                    .startObject()
                    .field("title", text.getTitle())
                    .field("feed-source", text.getSource().getFeedSource())
                    .field("lang", text.getSource().getLanguage())
                    .field("description", text.getDescription())
                    .field("article", text.getArticle())
                    .field("author", text.getAuthor())
                    .field("link", text.getLink())
                    .field("site-text", text.getSpecificText())
                    .field("tags", Arrays.toString(text.getTags()))
                    .field("text-source-url", text.getTextSourceUrl())
                    .field("timestamp", text.getTimestamp())
                    .field("timestamp-long", text.getTimestamp().getTime())
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(builder!=null) {
            request.source(builder);
            try {
                IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void printAllTexts() {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(searchResponse.getHits());
    }

    @Override
    public List<Text> getAllTexts() {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Text> allTexts = new ArrayList<>();
        for(SearchHit hit : searchResponse.getHits().getHits()){
            Map<String,Object> sources = hit.getSourceAsMap();
            Text text = new Text((String)sources.get("text-source-url"), new Date((long)sources.get("timestamp-long")), (String)sources.get("author"), (String)sources.get("description"), (String)sources.get("title"), (String)sources.get("article"), (String)sources.get("site-text"), new String[0],
                    new Source((String)sources.get("feed-source"),new Locale((String)sources.get("lang"))), (String)sources.get("link"));
            allTexts.add(text);
        }
        return allTexts;
    }

    public List<String> getAllLangTexts(String lang, int fromDay, int toDay) {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery()
                .filter(QueryBuilders.rangeQuery("timestamp-long").gte(new Date().getTime()-fromDay*86400000l))
                .filter(QueryBuilders.rangeQuery("timestamp-long").lte(new Date().getTime()-toDay*86400000l))
                .filter(QueryBuilders.termQuery("lang",lang));
        searchSourceBuilder.query(query);
        searchSourceBuilder.size(300);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> allTexts = new ArrayList<>();
        for(SearchHit hit : searchResponse.getHits().getHits()){
            Map<String,Object> sources = hit.getSourceAsMap();
            if(sources.get("title")!=null) {
                allTexts.add((String) sources.get("title"));
            }
            if(sources.get("site-text")!=null) {
                allTexts.add((String) sources.get("site-text"));
            }
            if(sources.get("description")!=null) {
                allTexts.add((String) sources.get("description"));
            }
        }
        return allTexts;
    }

    @Override
    public List<Text> getAllEnglishTexts() {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Text> allTexts = new ArrayList<>();
        for(SearchHit hit : searchResponse.getHits().getHits()){
            Map<String,Object> sources = hit.getSourceAsMap();
            Text text = new Text((String)sources.get("text-source-url"), new Date((long)sources.get("timestamp-long")), (String)sources.get("author"), (String)sources.get("description"), (String)sources.get("title"), (String)sources.get("article"), (String)sources.get("site-text"), new String[0],
                    new Source((String)sources.get("feed-source"),new Locale((String)sources.get("lang"))), (String)sources.get("link"));
            allTexts.add(text);
        }
        return allTexts;
    }

    @Override
    public void getAllSources() {

    }

    @Override
    public void close() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

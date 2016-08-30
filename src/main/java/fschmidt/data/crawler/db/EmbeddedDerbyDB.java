package fschmidt.data.crawler.db;

import fschmidt.data.crawler.model.Source;
import fschmidt.data.crawler.model.Text;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.LocaleUtils;

/**
 * This class handels the communication with an embedded database, which stores crawled texts. It is implemented as singleton.
 *
 * @author fschmidt
 */
public class EmbeddedDerbyDB {

    private static EmbeddedDerbyDB instance;
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private String database = "CrawlerDB";
    private Connection connection;

    private EmbeddedDerbyDB(String path) {
        try {
            this.database = path + "CrawlerDB";
            Class.forName(DRIVER).newInstance();
            connection = DriverManager.getConnection("jdbc:derby:" + database + ";create=true");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(EmbeddedDerbyDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Gets an instance of this class, conntected to a given embedded derby database at the given path.
     *
     * @param path The directory path to the embedded derby database.
     * @return
     */
    public static EmbeddedDerbyDB getInstance(String path) {
        if (instance == null) {
            instance = new EmbeddedDerbyDB(path);
        }
        return instance;
    }

    /**
     * This method creates all needed tables to a given embedded database.
     */
    public void createDatabase() {
        try {
            DatabaseMetaData metas = connection.getMetaData();
            ResultSet textsTables = metas.getTables(connection.getCatalog(), null, "texts", null);
            if (!textsTables.next()) {
                connection.createStatement().execute(
                        "create table texts(title CLOB not null, article CLOB, textSourceUrl CLOB not null, timestamp bigint not null, author CLOB, description CLOB, source CLOB, tags CLOB)");
            }
            ResultSet sourcesTables = metas.getTables(connection.getCatalog(), null, "sources", null);
            if (!sourcesTables.next()) {
                connection.createStatement().execute("create table sources(feedSource CLOB not null, language varchar(150) not null)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmbeddedDerbyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method adds new crawled texts to the database. First it checks, if a given text is already stored in the database. If not, the
     * text is added to the database.
     *
     * @param text The text to be added to the database. Only new texts, which are different in the title, will be stored.
     */
    public void addText(Text text) {
        String insertSourcesTableSQL = "INSERT INTO sources"
                + "(feedSource, language) VALUES"
                + "(?,?)";
        String insertTextsTableSQL = "INSERT INTO texts"
                + "(title, article, textSourceUrl, timestamp, author, description, source, tags) VALUES"
                + "(?,?,?,?,?,?,?,?)";

        try {
            String findSources = "select * from sources where feedSource LIKE ?";
            PreparedStatement preparedSS = connection.prepareStatement(findSources);
            preparedSS.setString(1, text.getSource().getFeedSource());
            ResultSet sourceResults = preparedSS.executeQuery();
            if (!sourceResults.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement(insertSourcesTableSQL);
                preparedStatement.setString(1, text.getSource().getFeedSource());
                preparedStatement.setString(2, text.getSource().getLanguage().getLanguage());
                preparedStatement.executeUpdate();
            }

            String findTextByTitle = "select * from texts where title LIKE ?";
            PreparedStatement preparedS = connection.prepareStatement(findTextByTitle);
            preparedS.setString(1, text.getTitle());
            ResultSet rs = preparedS.executeQuery();

            if (!rs.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement(insertTextsTableSQL);
                preparedStatement.setString(1, text.getTitle());
                preparedStatement.setString(2, text.getArticle());
                preparedStatement.setString(3, text.getTextSourceUrl());
                preparedStatement.setLong(4, text.getTimestamp().getTime());
                preparedStatement.setString(5, text.getAuthor());
                preparedStatement.setString(6, text.getDescription());
                preparedStatement.setString(7, text.getSource().getFeedSource());
                preparedStatement.setString(8, Arrays.toString(text.getTags()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmbeddedDerbyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method prints all titles of the stored texts.
     */
    public void printAllTexts() {
        try {
            ResultSet results = connection.createStatement().executeQuery("select title from texts");
            System.out.println("count: " + results.getMetaData().getColumnCount() + " ");
            for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
                System.out.format("%100s", results.getMetaData().getColumnName(i) + " ; ");
            }
            while (results.next()) {
                System.out.println("");
                for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
                    System.out.format("%100s", results.getString(i) + " ; ");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmbeddedDerbyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method queries the embedded database for all stored texts and returnes a list of Text.class.
     *
     * @return All texts stored in the given database.
     */
    public List<Text> getAllTexts() {
        List<Text> texts = new ArrayList<>();
        try {
            ResultSet results = connection.createStatement().executeQuery("select * from texts");
            int counter = 0;
            while (results.next()) {
                String title = results.getString(1);
                String article = results.getString(2);
                String textSourceUrl = results.getString(3);
                long timestamp = results.getLong(4);
                String author = results.getString(5);
                String description = results.getString(6);
                String sourceUrl = results.getString(7);
                String findSource = "select * from sources where feedSource LIKE ?";
                PreparedStatement preparedS = connection.prepareStatement(findSource);
                preparedS.setString(1, sourceUrl);
                ResultSet rs = preparedS.executeQuery();
                Source source = null;
                if (rs.next()) {
                    String feedSource = rs.getString(1);
                    String language = rs.getString(2);
                    Locale local = LocaleUtils.toLocale(language);
                    source = new Source(feedSource, local);
                }
                String tags = results.getString(8);
                String[] tagsArray = tags.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

                Text text = new Text(textSourceUrl, new Date(timestamp), author, description, title, article, tagsArray, source);
                texts.add(text);

                counter++;
            }
            System.out.println(counter + " articles");
        } catch (SQLException ex) {
            Logger.getLogger(EmbeddedDerbyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return texts;
    }

    /**
     * This method retrieves all texts, which are tagged with the English language.
     *
     * @return English texts from the database.
     */
    public List<Text> getAllEnglishTexts() {
        List<Text> texts = getAllTexts();
        List<Text> englishTexts = new ArrayList<>();
        for (Text text : texts) {
            if (text.getSource().getLanguage().getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                englishTexts.add(text);
                System.out.println(text.getDescription());
            }
        }
        System.out.println(englishTexts.size() + " english articles");
        return englishTexts;
    }

    /**
     * This method prints all feed sources in the database.
     */
    public void getAllSources() {
        try {
            ResultSet results = connection.createStatement().executeQuery("select feedSource from sources");
            System.out.println("count: " + results.getMetaData().getColumnCount() + " ");
            for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
                System.out.format("%100s", results.getMetaData().getColumnName(i) + " ; ");
            }
            while (results.next()) {
                System.out.println("");
                for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
                    System.out.format("%100s", results.getString(i) + " ; ");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmbeddedDerbyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method closes the connection to a given database.
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmbeddedDerbyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

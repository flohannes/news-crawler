package fschmidt.data.crawler.db;

import fschmidt.data.crawler.model.Text;

import java.util.List;

public interface DatabaseConnection {
    public void createDatabase();
    public void addText(Text text);
    public void printAllTexts();
    public List<Text> getAllTexts();
    public List<Text> getAllEnglishTexts();
    public void getAllSources();
    public void close();
}

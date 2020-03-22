package fschmidt.data.crawler;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import fschmidt.data.crawler.db.ElasticSearch;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import org.jcodec.api.awt.AWTSequenceEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateWordcloudMain {
    public static void main(String... args){
        if ((args == null) || args.length != 4) {
            System.err.println("Parameters: <database path> <media path> <fromDay> <toDay>");
            System.exit(0);
        }
        String dbPath = args[0];
        String imagePath = args[1];
        int fromDay = Integer.valueOf(args[2]);
        int toDay = Integer.valueOf(args[3]);

        System.out.println("create images");
        try {
            createImage("en",dbPath,imagePath,fromDay,toDay);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            createImage("de",dbPath,imagePath,fromDay,toDay);
        } catch (IOException e) {
            e.printStackTrace();
        }

        createVideo("en",imagePath,fromDay,toDay);
        createVideo("de",imagePath,fromDay,toDay);

        ElasticSearch.getInstance(dbPath).close();
    }

    private static void createImage(String lang, String dbpath, String imagePath, int fromDay, int toDay) throws IOException {
        for(int j = fromDay; j > toDay-1; j--) {
            List<String> texts = ElasticSearch.getInstance(dbpath).getAllLangTexts(lang, j, j-1);

            InputStream in = CreateWordcloudMain.class.getResourceAsStream("/" + lang + "-pos-maxent.bin");
            POSModel posModel = new POSModel(in);
            POSTaggerME tagger = new POSTaggerME(posModel);
            Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
            if(texts.isEmpty()){
                continue;
            }
            String selectedTokensAll = "";
            for (String text : texts) {
                String tokens[] = tokenizer.tokenize(text);
                String tags[] = tagger.tag(tokens);

                List<String> selectedTokens = new ArrayList<>();

                for (int i = 0; i < tokens.length; i++) {
                    if (tags[i].equals("NN") || tags[i].equals("NNP")) {
                        selectedTokens.add(tokens[i]);
                        selectedTokensAll += " " + tokens[i];
                    }
                }
                selectedTokensAll += ".";
            }

            InputStream is = new ByteArrayInputStream(selectedTokensAll.getBytes(StandardCharsets.UTF_8));
            final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
            frequencyAnalyzer.setWordFrequenciesToReturn(300);
            frequencyAnalyzer.setMinWordLength(5);
            final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(is);
            final Dimension dimension = new Dimension(600, 600);
            final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
            wordCloud.setPadding(2);
            wordCloud.setBackground(new CircleBackground(300));
            wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
            wordCloud.setFontScalar(new SqrtFontScalar(10, 40));//)SqrtFontScalar(
            wordCloud.build(wordFrequencies);
            wordCloud.writeToFile(imagePath + "datarank_wordcloud_circle_" + lang + "_" + j + ".png");
            in.close();
        }
    }

    private static void createVideo(String lang, String imagePath, int fromDay, int toDay){
        List<BufferedImage> images = new ArrayList<>();
        for(int j = fromDay; j > toDay-1; j--) {
            String currentImagePath = imagePath + "datarank_wordcloud_circle_" + lang + "_" + j + ".png";
            try {
                BufferedImage img = ImageIO.read(new File(currentImagePath));
                images.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd-hh:mm:ss");
        String strDate = dateFormat.format(today);
        AWTSequenceEncoder encoder = null; // 25 fps
        try {
            encoder = AWTSequenceEncoder.createSequenceEncoder(new File(imagePath+"wordcloudVid_"+strDate+".mp4"), 1);
            for (BufferedImage image : images) {
                encoder.encodeImage(image);
            }
            encoder.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

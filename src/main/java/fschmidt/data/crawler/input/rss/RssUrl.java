package fschmidt.data.crawler.input.rss;

import fschmidt.data.crawler.model.Source;
import java.util.Locale;

/**
 *
 * @author Florian
 */
public enum RssUrl {

//    thetroublewithpoet(new Source(
//            "https://www.blogger.com/feeds/7008437821927098215/posts/default?redirect=false&start-index=1&max-results=10", Locale.ENGLISH),
//            new DefaultWebsiteText()),
//    telltheirstory(new Source("https://www.blogger.com/feeds/558286022617189769/posts/default?redirect=false&start-index=1&max-results=10",
//            Locale.ENGLISH), new DefaultWebsiteText()),
//    oregonecon(new Source("https://www.blogger.com/feeds/3471471289744825428/posts/default?redirect=false&start-index=1&max-results=10",
//            Locale.ENGLISH), new DefaultWebsiteText()),
//    wholehealthsource(new Source(
//            "https://www.blogger.com/feeds/1629175743855013102/posts/default?redirect=false&start-index=1&max-results=10",
//            Locale.ENGLISH), new DefaultWebsiteText()),
//    tomandatticus(new Source("https://www.blogger.com/feeds/36795530/posts/default?redirect=false&start-index=1&max-results=10",
//            Locale.ENGLISH),
//            new DefaultWebsiteText()),
//    cliffmass(new Source("https://www.blogger.com/feeds/7478606652950905956/posts/default?redirect=false&start-index=1&max-results=10",
//            Locale.ENGLISH), new DefaultWebsiteText()),
//    orangette(new Source("https://www.blogger.com/feeds/7793856/posts/default?redirect=false&start-index=1&max-results=10", Locale.ENGLISH),
//            new DefaultWebsiteText()),
    computationalcomplexity(new Source("https://www.blogger.com/feeds/3722233/posts/default?redirect=false&start-index=1&max-results=10",
            Locale.ENGLISH), new DefaultWebsiteText()),
    googleblog(
            new Source("https://www.blogger.com/feeds/10861780/posts/default?redirect=false&start-index=1&max-results=10", Locale.ENGLISH),
            new DefaultWebsiteText()),
//    museumtwo(new Source("https://www.blogger.com/feeds/37032121/posts/default?redirect=false&start-index=1&max-results=10", Locale.ENGLISH),
//            new DefaultWebsiteText()),
//    philosophicalcomment(new Source(
//            "https://www.blogger.com/feeds/6763377479629539589/posts/default?redirect=false&start-index=1&max-results=10",
//            Locale.ENGLISH), new DefaultWebsiteText()),
//    shadesofgreen(new Source("https://www.blogger.com/feeds/2035975229081483385/posts/default?redirect=false&start-index=1&max-results=10",
//            Locale.ENGLISH), new DefaultWebsiteText()),
//    nadnutsshortstories(new Source("https://www.blogger.com/feeds/11529337/posts/default?redirect=false&start-index=1&max-results=10",
//            Locale.ENGLISH),
//            new DefaultWebsiteText()),
//    storywheel(new Source("https://www.blogger.com/feeds/812018966351611184/posts/default?redirect=false&start-index=1&max-results=10",
//            Locale.ENGLISH), new DefaultWebsiteText()),
//    mayontheshortstory(new Source(
//            "https://www.blogger.com/feeds/3161136885462262525/posts/default?redirect=false&start-index=1&max-results=10",
//            Locale.ENGLISH), new DefaultWebsiteText()),
    ibmresearchnews(
            new Source("https://www.blogger.com/feeds/3821464279005499761/posts/default?redirect=false&start-index=1&max-results=10",
                    Locale.ENGLISH), new DefaultWebsiteText()),
//    lowcarbnews(new Source("https://www.blogger.com/feeds/8165224834813913621/posts/default?redirect=false&start-index=1&max-results=10",
//            Locale.ENGLISH), new DefaultWebsiteText()),
//    movieon(new Source("https://www.blogger.com/feeds/33971765/posts/default?redirect=false&start-index=1&max-results=10", Locale.ENGLISH),
//            new DefaultWebsiteText()),
    SCIENCEMAG(new Source("http://news.sciencemag.org/rss/current.xml", Locale.ENGLISH), new ScienceMagWebsiteText()),
    NYTIMESFRONTPAGE(new Source("http://www.nytimes.com/services/xml/rss/nyt/HomePage.xml", Locale.ENGLISH), new DefaultWebsiteText()),
    REUTERSTOPNEWS(new Source("http://feeds.reuters.com/reuters/topNews.xml", Locale.ENGLISH), new ReutersWebsiteText()),
    REUTERSWORLDNEWS(new Source("http://feeds.reuters.com/Reuters/worldNews", Locale.ENGLISH), new ReutersWebsiteText()),
    REUTERSSPORTS(new Source("http://feeds.reuters.com/reuters/sportsNews", Locale.ENGLISH), new ReutersWebsiteText()),
    REUTERSLIFESTYLE(new Source("http://feeds.reuters.com/reuters/lifestyle", Locale.ENGLISH), new ReutersWebsiteText()),
    ACM_AI(new Source("http://cacm.acm.org/browse-by-subject/artificial-intelligence.rss", Locale.ENGLISH), new DefaultWebsiteText()),
    BBCFRONTPAGE(new Source("http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/front_page/rss.xml", Locale.ENGLISH),
            new DefaultWebsiteText()),
    //    BBCWORLD("http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/world/rss.xml"),
    BBCHEALTH(new Source("http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/health/rss.xml", Locale.ENGLISH), new DefaultWebsiteText()),
    //    ACM_NEWS("http://cacm.acm.org/news.rss"),
    //    PUBMED_MEDICALDECISIONMAKING(
    //            new Source("http://www.ncbi.nlm.nih.gov/entrez/eutils/erss.cgi?rss_guid=1RwSy84-6Zty-ddB-feonz-oKX8FlOmd4sDzaRHf_hsdwy_ABs",
    //            Locale.ENGLISH), new PubMedWebsiteText()),
    //    REUTERSWORLDNEWS("http://feeds.reuters.com/Reuters/worldNews.xml");
    ZEIT(new Source("http://newsfeed.zeit.de/index", Locale.GERMAN), new ZeitWebsiteText()),
    //    OAMed(new Source("http://www.online-artikel.de/rss.php?feed&c=27&n=all&nc=200", Locale.GERMAN), new DefaultWebsiteText()),
    PHARMAPREIS(new Source("http://www.pharmapreisvergleich.de/news-feed", Locale.GERMAN), new DefaultWebsiteText()),
    ZAHNMED(new Source("http://www.zahnarztpfaffenhofen.de/assets/rss/666055a4a70fcb901.xml", Locale.GERMAN), new DefaultWebsiteText()),
    HISTAMIN(new Source("http://www.histaminunvertraeglichkeit.net/blog/feed", Locale.GERMAN), new DefaultWebsiteText()),
    AERZTEZEITUNG(new Source("http://www.aerztezeitung.de/extras/rss/?cat=medizin", Locale.GERMAN), new AerzteZeitungWebsiteText()),
    SPRINGERMED(new Source("http://www.springermedizin.at/cms/rss.php", Locale.GERMAN), new SpringerMedWebsiteText()),
//    PUBMED_HCC(new Source("http://www.ncbi.nlm.nih.gov/entrez/eutils/erss.cgi?rss_guid=1BsDMEWA_ZXjDgIGJiLJwmhs8MfJk6YAoNZ_QRB3WYaR5agUhP",
//            Locale.GERMAN), new DefaultWebsiteText()),
    //    AOK_CLARIMEDIS(new Source("https://www.aok.de/rss.xml", Locale.GERMAN), new AokClarimedisWebsiteText()),
    SPRINGER_INNERE(new Source("http://www.springermedizin.at/cms/rss.php?feed=349", Locale.GERMAN), new SpringerMedWebsiteText()),
    SCINEXX(new Source("http://feeds.feedburner.com/scinexx", Locale.GERMAN), new DefaultWebsiteText()),
    STERN(new Source("http://www.stern.de/standard/rss.php?channel=all", Locale.GERMAN), new DefaultWebsiteText()),
    WELT(new Source("http://www.welt.de/?service=Rss", Locale.GERMAN), new DefaultWebsiteText()),
    IDW_MED(new Source(
            "https://idw-online.de/de/pressreleasesrss?langs=de_DE&category_ids=4&category_ids=10&category_ids=7&category_ids=1&category_ids=5&field_ids=100&field_ids=101&field_ids=401&field_ids=400&field_ids=606",
            Locale.GERMAN), new DefaultWebsiteText()),
    BLOGMED(new Source("http://www.blogmed.de/feed/", Locale.GERMAN), new BlogMedWebsiteText()),
    ALLERGIES(new Source("http://www.medicinenet.com/rss/general/allergies.xml", Locale.GERMAN), new DefaultWebsiteText()),
    ASTHMA(new Source("http://www.medicinenet.com/rss/general/asthma.xml", Locale.GERMAN), new DefaultWebsiteText()),
    HEISE(new Source("http://www.heise.de/newsticker/heise-atom.xml", Locale.GERMAN), new DefaultWebsiteText()),
    //    BZ(new Source("http://berliner-zeitung.feedsportal.com/c/35000/f/646697/index.rss", Locale.GERMAN), new BZWebsiteText()),
    FAZ(new Source("http://www.faz.net/rss/aktuell/", Locale.GERMAN), new FAZWebsiteText()),
    //    BILD(new Source("http://rss.bild.de/bild.xml", Locale.GERMAN), new DefaultWebsiteText()),
    SPIEGEL_ONLINE(new Source("http://www.spiegel.de/schlagzeilen/index.rss", Locale.GERMAN), new SpiegelOnlineWebsiteText()),
    SUEDDEUTSCHE(new Source("http://rss.sueddeutsche.de/app/service/rss/alles/index.rss?output=rss", Locale.GERMAN),
            new SueddeutscheWebsiteText()),
    DW(new Source("http://rss.dw.com/xml/rss-de-all", Locale.GERMAN), new DWWebsiteText()),
    YAHOO(new Source("http://news.yahoo.com/rss/", Locale.ENGLISH), new DefaultWebsiteText()),
    //    YAHOO_DE(new Source("https://de.nachrichten.yahoo.com/rss/", Locale.GERMAN), new DefaultWebsiteText()),
    WEB_DE(new Source("http://web.de/feeds/rss/index.rss", Locale.GERMAN), new WebDeWebsiteText()),
    FACHARZT(new Source("http://www.facharzt.de/pub/gesundheitspolitik.rss", Locale.GERMAN), new DefaultWebsiteText()),
    AERZTEBLATT(new Source("https://www.aerzteblatt.de/rss/news.asp", Locale.GERMAN), new AerzteblattWebsiteText()),
    //    HOCHDRUCKLIGA_AERZTE(new Source("http://www.hochdruckliga.de/dhl-feedkombi-aerzte.xml", Locale.GERMAN), new HochdruckligaWebsiteText()),
    //    HOCHDRUCKLIGA_PATIENTEN(new Source("http://www.hochdruckliga.de/dhl-feedkombi-betroffene.xml", Locale.GERMAN), new HochdruckligaWebsiteText()),
    //    HOCHDRUCKLIGA_PRESSE(new Source("http://www.hochdruckliga.de/dhl-feedkombi-presse.xml", Locale.GERMAN), new HochdruckligaWebsiteText()),
    HILLARY_CLINTON_GOOGLE_NEWS(new Source("https://news.google.com/news?ned=us&hl=en&output=rss&num=20&q=Hillary+Clinton",
            Locale.ENGLISH), new DefaultWebsiteText()),
    TRUMP_GOOGLE_NEWS(new Source("https://news.google.com/news?ned=us&hl=en&output=rss&num=5&q=Donald+Trump", Locale.ENGLISH),
            new DefaultWebsiteText()),
    GOOGLE_GOOGLE_NEWS(new Source("https://news.google.de/news/feeds?pz=1&cf=all&ned=en&hl=en&q=Google&output=rss&num=20", Locale.ENGLISH),
            new DefaultWebsiteText()),
    APPLE_GOOGLE_NEWS(new Source("https://news.google.de/news/feeds?pz=1&cf=all&ned=en&hl=en&q=Apple&output=rss&num=20", Locale.ENGLISH),
            new DefaultWebsiteText()),
    IOS_GOOGLE_NEWS(new Source("https://news.google.de/news/feeds?pz=1&cf=all&ned=us&hl=us&q=ios&output=rss&num=20", Locale.ENGLISH),
            new DefaultWebsiteText()),
    ANDROID_GOOGLE_NEWS(new Source("https://news.google.de/news/feeds?pz=1&cf=all&ned=us&hl=us&q=android&output=rss&num=20", Locale.ENGLISH),
            new DefaultWebsiteText()),
    POKEMON_GO_GOOGLE_NEWS(new Source("https://news.google.com/news?ned=us&hl=en&output=rss&num=20&q=Pokemon+Go", Locale.ENGLISH),
            new DefaultWebsiteText()),
    TOP_STORIES_GOOGLE_NEWS(new Source("https://news.google.com/news?ned=us&hl=en&output=rss&num=20", Locale.ENGLISH),
            new DefaultWebsiteText()),
    HILLARY_CLINTON_REDDIT(new Source("https://www.reddit.com/r/hillaryclinton/.rss", Locale.ENGLISH), new DefaultWebsiteText()),
    WORLDNEWS_REDDIT(new Source("https://www.reddit.com/r/worldnews/.rss", Locale.ENGLISH), new DefaultWebsiteText()),
    TRUMP_REDDIT(new Source("https://www.reddit.com/r/The_Donald/.rss", Locale.ENGLISH), new DefaultWebsiteText()),
    POKEMON_GO_REDDIT(new Source("https://www.reddit.com/r/pokemongo/.rss", Locale.ENGLISH), new DefaultWebsiteText()),
    NEWS_REDDIT(new Source("https://www.reddit.com/r/news/.rss", Locale.ENGLISH), new DefaultWebsiteText()),
    TECHNOLOGY_REDDIT(new Source("https://www.reddit.com/r/technology/.rss", Locale.ENGLISH), new DefaultWebsiteText()),
    POKEMON_REDDIT(new Source("https://www.reddit.com/r/pokemon/.rss", Locale.ENGLISH), new DefaultWebsiteText()),
    FRONTPAGE_REDDIT(new Source("https://www.reddit.com/.rss", Locale.ENGLISH), new DefaultWebsiteText()),
    BREXIT_REDDIT(new Source("https://www.reddit.com/r/brexit/.rss", Locale.ENGLISH), new DefaultWebsiteText()),
    BREXIT_GOOGLE_NEWS(new Source("https://news.google.com/news?ned=us&hl=en&output=rss&num=20&q=Brexit", Locale.ENGLISH), new DefaultWebsiteText());

    private Source source = null;
    private RetrieveWebsiteText websiteText = null;

    private RssUrl(Source source, RetrieveWebsiteText websiteText) {
        this.setSource(source);
        this.setWebsiteText(websiteText);
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void setWebsiteText(RetrieveWebsiteText websiteText) {
        this.websiteText = websiteText;
    }

    public RetrieveWebsiteText getWebsiteText() {
        return websiteText;
    }
}

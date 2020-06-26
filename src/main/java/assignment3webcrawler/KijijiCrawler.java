package assignment3webcrawler;

import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.http.Header;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

public class KijijiCrawler {
  private static KijijiCrawler kijijiCrawlerInstance = null;
  protected String seed="";
  
  public static KijijiCrawler returnKijijiCrawlerInstance() {
    if (kijijiCrawlerInstance == null) {
      kijijiCrawlerInstance = new KijijiCrawler();
    }
    return kijijiCrawlerInstance;
  }
  
  public void crawlFromSeed(String seed, int limit) throws Exception {
    // Crawl from the seed (but not the seed)
    this.seed = seed;
    // Instantiate the controller for this crawl.
    CrawlConfig config = new CrawlConfig();
    config.setCrawlStorageFolder("/tmp/crawler4j/");
    // Set the limit. Add 1 because we skip the page we start on.
    config.setMaxPagesToFetch(limit + 1);
    config.setMaxDepthOfCrawling(1);
    PageFetcher pageFetcher = new PageFetcher(config);
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
    // Add the seed
    controller.addSeed(seed);
    
    // Number of threads to use during crawling. Increasing this typically makes crawling faster. But crawling
    // speed depends on many other factors as well. You can experiment with this to figure out what number of
    // threads works best for you.
    int numberOfCrawlers = 8;

    // The factory which creates instances of crawlers.
    CrawlController.WebCrawlerFactory<Crawler> factory = Crawler::new;
    controller.start(factory, numberOfCrawlers);
  }

  
  
  private class Crawler extends WebCrawler {
    // Crawler will not retrieve pages with these extensions
    private final Pattern FILTERS = Pattern.compile(".*(\\.(css|js))$");
    
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (FILTERS.matcher(href).matches()) {
          return false;
        }
        // Crawl only if its on kijiji and seed doesn't have a <FILTERS>
        // extension
        return href.startsWith("https://www.kijiji.ca/v");
    }
    
    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
      String url = page.getWebURL().getURL();
      System.out.println(url);

      if (page.getParseData() instanceof HtmlParseData) {
        if (!(seed.equals(url))) {
          HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
          Set<WebURL> links = htmlParseData.getOutgoingUrls();
          // Send to KijijiParser
          KijijiParser parserObj = KijijiParser.returnKijijiParserInstance();
          try {
            parserObj.parseThroughHTML(url);
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
      }
      }
    }
  }
  
  public static void main(String[] args) throws Exception {
    String seed = "https://www.kijiji.ca/b-city-of-toronto/house/k0l1700273?dc=true";
    int limit = 10;
    KijijiCrawler kj = KijijiCrawler.returnKijijiCrawlerInstance();
    kj.crawlFromSeed(seed, limit);
  }
}
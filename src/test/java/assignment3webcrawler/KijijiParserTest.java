package assignment3webcrawler;

import database.DatabaseRetriever;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;


public class KijijiParserTest {
  KijijiCrawler crawlerObj;
  String url, expected, actual;
  DatabaseRetriever dbr;
  List<KijijiData> data;

  @Before
  public void setUp() throws Exception {
    crawlerObj = mock(KijijiCrawler.class);
    actual = expected = "";
    // Check database
    dbr = DatabaseRetriever.returnDatabaseRetriever();
  }

  @After
  public void tearDown() throws Exception {
  }

  /*
  @Test
  public void testLink1() throws Exception {
    String link = "https://www.kijiji.ca/v-apartments-condos/city-of-toronto/3-br-house-main-floor-for-rent-in-scarb-mccowan-ellesmere/1448007287?enableSearchNavigationFlag=true";
    KijijiParser yea = KijijiParser.returnKijijiParserInstance();
    yea.parseThroughHTML(link);
    data = dbr.getFromDatabase("jdbc:sqlite:./src/test/test.sqlite");
    System.out.println(data);
    fail();
  }
  */

}

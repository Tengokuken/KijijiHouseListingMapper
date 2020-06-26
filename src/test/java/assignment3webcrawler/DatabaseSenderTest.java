package assignment3webcrawler;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Test;
import database.DatabaseRetriever;
import database.DatabaseSender;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

public class DatabaseSenderTest {
  String actual, expected;
  String dbURL = "jdbc:sqlite:src/test/test.sqlite";
  
  @After
  public void breakDown() throws SQLException {
    Connection db = DriverManager.getConnection(dbURL);
    Statement stmt = db.createStatement();
    String sql = "DELETE FROM KijijiHousing";
    stmt.executeUpdate(sql);
    stmt.close();
    db.close();
  }
  
  @Test
  public void test() {
    KijijiData mkd = new KijijiData.Builder("a", "", "", "", "").build();
    DatabaseSender dbs = DatabaseSender.returnDatabaseSender();
    DatabaseRetriever dbr = DatabaseRetriever.returnDatabaseRetriever();
    dbs.sendToDatabase(mkd, dbURL);
    List<KijijiData> gotten = dbr.getFromDatabase(dbURL);
    expected = "";
    actual = gotten.get(0).getAddress();
    assertEquals(expected, actual);
  }

  @Test
  public void testWithBuilder() {
    KijijiData kd = new KijijiData.Builder("ad", 
        "address","linkToListing", "date", "price")
        .unitType("unit")
        .petsAllowed("yes")
        .numBathrooms("3")
        .numBathrooms("199")
        .parking("3")
        .size("63")
        .furnished("0")
        .yard("no")
        .balcony("0")
        .elevator("1")
        .hydro("0")
        .heating("no")
        .water("1")
        .cable("0")
        .internet("1")
        .landline("1")
        .build();
    DatabaseSender dbs = DatabaseSender.returnDatabaseSender();
    DatabaseRetriever dbr = DatabaseRetriever.returnDatabaseRetriever();
    dbs.sendToDatabase(kd, dbURL);
    List<KijijiData> gotten = dbr.getFromDatabase(dbURL);
    expected = "address";
    actual = gotten.get(0).getAddress();
    assertEquals(expected, actual);
    expected = "no";
    actual = gotten.get(0).getHeating();
    assertEquals(expected, actual);
  }
}

package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import assignment3webcrawler.KijijiData;

public class DatabaseSender extends DatabaseConnector {

  // Singleton instance
  private static DatabaseSender databaseSenderInstance;
  
  public static DatabaseSender returnDatabaseSender() {
	/*
	 * Singleton constructor
	 */
    if (databaseSenderInstance == null) {
      databaseSenderInstance = new DatabaseSender();
    }
    return databaseSenderInstance;
  }
  
  public void sendToDatabase(KijijiData data, String url) {
	/*
	 * Send information in the input KijijiData to the database 
	 */
    // set up database connection
    this.connectToDatabase(url);
    Connection db = this.getDatabase();

    HashMap<String, Object> availableData = data.getAvailableData();

	String insertInto = "INSERT INTO KijijiHousing (";
	String values = ") VALUES (";
	
	int i = 1;
	int size = availableData.size();

	// concatenate all available information to one insert statement
    for (@SuppressWarnings("rawtypes") Map.Entry dataElement : 
                                        availableData.entrySet()) {
    	// get key as the column, value as the value for the sql command
    	String column = (String) dataElement.getKey();
    	// convert non-null value into string, skip null values
    	Object value = dataElement.getValue();
    	if (value != null) {
          insertInto += column;
          values += "'" + value.toString() + "'";
          if (i < size) {
            insertInto += ",";
            values += ",";
          }
    	}
    	i++;
    }

    // execute sql statement
    String sql = insertInto + values + ")";
    this.executeUpdate(db, sql);
    // close database connection
    this.closeDatabase();
  }

  private void executeUpdate(Connection db, String sql) {
	  /*
	   * Execute the input sql statement using the database connection
	   */
	  try {
		  Statement stmt = db.createStatement();
		  // execute sql statement for inserting or updating
		  System.out.println("EXECUTED: " + sql);
		  stmt.executeUpdate(sql);
		  stmt.close();
	} catch (SQLException e) {
		  e.printStackTrace();
	}
  }

  // SAMPLE
  public static void main(String[] args) {
    /*
	  	KijijiData kd = new KijijiData.Builder("hey", 
	  			"nearestCity", "address", "durationOfStay","linkToListing", 
	  			"datePosted", "unitType", "1", "2", "false")
	  	        .parking("3")
	  	        .size("63")
	  	        .price("10")
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
	  	
	  	DatabaseSender ds = DatabaseSender.returnDatabaseSender("jdbc:sqlite:./Assignment3.sqlite");
	  	ds.sendToDatabase(kd);
	  	ds.closeDatabase();
	  	*/
	}
  
}

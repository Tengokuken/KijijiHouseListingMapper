package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

  // database connection instance
  protected Connection database;

  protected void connectToDatabase(String url) {
    /*
     * Set up the database connection to Assignment3.sqlite
     */
	  try {
		  this.database = DriverManager.getConnection(url);
	} catch (SQLException e) {
		  System.out.println(e.getMessage());
	}
  }

  protected Connection getDatabase() {
    /*
     * Return the database connection
     */
	  return database;
  }
  
  protected void closeDatabase() {
	  /*
	   * Close the database connection 
	   */
	  try {
		this.getDatabase().close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
  }
  
}

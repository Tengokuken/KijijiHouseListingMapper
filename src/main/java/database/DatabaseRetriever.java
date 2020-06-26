package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import assignment3webcrawler.KijijiData;

public class DatabaseRetriever extends DatabaseConnector {

  // Singleton instance	
  private static DatabaseRetriever databaseRetrieverInstance;  
  
  public static DatabaseRetriever returnDatabaseRetriever() {
    /*
     * Singleton constructor
     */
	  if (databaseRetrieverInstance == null) {
		  databaseRetrieverInstance = new DatabaseRetriever();
		  }
	  return databaseRetrieverInstance;
  }

  public List<KijijiData> getFromDatabase(String url) {
	  /*
	   * Return List of KijijiData
	   */
      // set up database connection
      this.connectToDatabase(url);
	  Connection db = this.getDatabase();

	  // set up the list to be returned
	  List<KijijiData> dataList = new ArrayList<KijijiData>();

	  try {
	    // get all information from the database
		Statement query = db.createStatement();
		ResultSet rs = query.executeQuery("SELECT * FROM KijijiHousing");

		// iterate through each row in the table
		while (rs.next()) {
			String adTitle = rs.getString("adTitle");
			String address = rs.getString("address");
			String linkToListing = rs.getString("linkToListing");
			String datePosted = rs.getString("datePosted");
            String price = rs.getString("price");
			String unitType = rs.getString("unitType");
			String numBedrooms = rs.getString("numBedrooms");
			String numBathrooms = rs.getString("numBathrooms");
			String petsAllowed = rs.getString("petsAllowed");
			String parking = rs.getString("parking");
			String size = rs.getString("size");
			String smoking = rs.getString("smoking");
			String furnished = rs.getString("furnished");
			String yard = rs.getString("yard");
			String balcony = rs.getString("balcony");
			String elevator = rs.getString("elevator");
			String hydro = rs.getString("hydro");
			String heating = rs.getString("heating");
			String water = rs.getString("water");
			String cable = rs.getString("cable");
			String internet = rs.getString("internet");
			String landline = rs.getString("landline");
			
			KijijiData data =  new KijijiData.Builder(adTitle, address,
		        linkToListing, datePosted, price)
		        .unitType(unitType)
		        .numBathrooms(numBathrooms)
		        .numBedrooms(numBedrooms)
		        .petsAllowed(petsAllowed)
		        .parking(parking)
		        .size(size)
		        .smoking(smoking)
		        .furnished(furnished)
		        .yard(yard)
		        .balcony(balcony)
		        .elevator(elevator)
		        .hydro(hydro)
		        .heating(heating)
		        .water(water)
		        .cable(cable)
		        .internet(internet)
		        .landline(landline)
		        .build();
			dataList.add(data);
		}
		// close the current query statement
		query.close();
	  } catch (SQLException e) {
		e.printStackTrace();
		}
	  // close database and return the list of KijijiData
      this.closeDatabase();
	  return dataList;
  }

  // SAMPLE
  public static void main(String[] args) {
	  DatabaseRetriever dr = DatabaseRetriever.returnDatabaseRetriever();
//	  dr.getFromDatabase();
	  System.out.println(dr.getFromDatabase("jdbc:sqlite:./Assignment3.sqlite"));
  }

}

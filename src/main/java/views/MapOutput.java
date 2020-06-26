	package views;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import assignment3webcrawler.KijijiData;
import assignment3webcrawler.KijijiParser;
import database.DatabaseConnector;
import database.DatabaseRetriever;

public class MapOutput extends DataOutput {
  private final String key = "AIzaSyDxNkm4XMiYBUggyA6bJJyqeJ9U2nXTyos";
  private static MapOutput mapOutputInstance = null;
  
  public static MapOutput returnMapOutputInstance() {
    if (mapOutputInstance == null)
      mapOutputInstance = new MapOutput();
    return mapOutputInstance;
  }
  
  @Override
  public void outputToView() throws ApiException, InterruptedException, IOException {
    DatabaseRetriever dbr = DatabaseRetriever.returnDatabaseRetriever();
    List<KijijiData> testData = dbr.getFromDatabase("jdbc:sqlite:./Assignment3.sqlite");
    this.setData(testData);
    // Get the address of the KijijiData object
    GeoApiContext context = new GeoApiContext.Builder()
        .apiKey(key)
        .build();
    String outputToJSON = "";
    JSONArray jsonResponse = new JSONArray();
    for (KijijiData listing : this.data) {
      // Skip this if theres no location data
      if (listing.getAddress().equals(""))
          continue;
        System.out.println(listing);
        JSONObject jsonObj = new JSONObject();
    	String address = (String) listing.getAddress();
      GeocodingResult[] results =  GeocodingApi.geocode(context,
          address).await();
      Iterator it = listing.getAvailableData().entrySet().iterator();
      while (it.hasNext()) {
          Map.Entry pair = (Map.Entry)it.next();
          if (pair.getValue() != null) {
            jsonObj.put(pair.getKey(), pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException 
          }
      }
      double lat = results[0].geometry.location.lat;
      double lng = results[0].geometry.location.lng;
      jsonObj.put("lat", lat);
      jsonObj.put("lng", lng);
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      // TODO: Printing this gives weird stuff. maybe remove the \n\t chars?
      jsonResponse.add(jsonObj);
    }
    System.out.println(jsonResponse);
    
    //Write JSON file
    try (FileWriter file = new FileWriter("./src/main/webapp/mapMarkers.json")) {
        file.write(jsonResponse.toJSONString());
        file.flush();
        file.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  

  public static void main(String[] args) throws ApiException, InterruptedException, IOException {
    MapOutput mpo = new MapOutput();/*
    KijijiData dataPoint = new KijijiData.Builder("adTitle", "nearestCity", "address", "durationOfStay",
        "linkToListing", "datePosted", "unitType", "bed", "bath", "no dog")
        .parking("like 1 tricycle")
        .size("63")
        .price("10")
        .smoking("no smoke")
        .furnished("none")
        .yard("where??")
        .balcony("nah")
        .elevator("bungalow")
        .hydro("no water")
        .heating("no fire")
        .water("no")
        .cable("lol imagine watching tv in 2019")
        .internet("u wish")
        .landline("???")
        .build();
    KijijiData dataPoint2 = new KijijiData.Builder("addTitle", "nearesttCity", "University of Toronto Scarborough", "durati0nOfStay",
        "l0nk2Listing", "datebosted", "youknitType", "bath", "bed", "can dog?")
        .parking("at least 5 trains")
        .size("big")
        .price("10")
        .smoking("no smoke 5 feet from the campus")
        .furnished("Couches in BV")
        .yard("Rogue Valley")
        .balcony("Rooftop garden")
        .elevator("Theres like a lot")
        .hydro("toronto hydro")
        .heating("its very cold so i wear sweaters")
        .water("water fountains nice")
        .cable("lol imagine watching tv in 2019")
        .internet("wifi kinda sucks")
        .landline("Call ext 555")
        .build();
    List<KijijiData> testData = new ArrayList<KijijiData>();
    
    testData.add(dataPoint);
    testData.add(dataPoint2);
    */
    DatabaseRetriever dbr =DatabaseRetriever.returnDatabaseRetriever();
    List<KijijiData> testData = dbr.getFromDatabase("jdbc:sqlite:./Assignment3.sqlite");
    mpo.setData(testData);
    mpo.outputToView();
  }
}

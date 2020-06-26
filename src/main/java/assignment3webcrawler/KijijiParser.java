package assignment3webcrawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import database.DatabaseSender;

public class KijijiParser {
  private static KijijiParser kijijiParserInstance = null;
  
  public static KijijiParser returnKijijiParserInstance() {
    if (kijijiParserInstance == null)
      kijijiParserInstance = new KijijiParser();
    return kijijiParserInstance;
  }
  
  public void parseThroughHTML(String url) throws IOException {
    // Use generics to store data
    HashMap<String, String> rentalInfo = new HashMap<String, String>();
    // Add linkToListing
    rentalInfo.put("Link To Listing", url);
    // Search in the html the fields required
    Document doc = Jsoup.connect(url).get();
    // Get the title, which contains important information
    // Order of elements: adTitle, durationOfStay, nearestCity
    String[] titleInf = doc.title().split("\\|");
    rentalInfo.put("Ad Title", titleInf[0]);
    // Get the date posted, which is provided by the title attribute of the time tag
    rentalInfo.put("Date Posted", doc.select("time[title]").attr("title"));
    // Get the location which is provided by the address-3617944557 class
    rentalInfo.put("Address", doc.getElementsByClass("address-3617944557").text());
    // Get the optional listed price, which is under the class currentPrice-441857624
    rentalInfo.put("Price", doc.getElementsByClass("currentPrice-441857624").text());
    // Get the information in the "Overview" (unitType, numBedrooms. numBathrooms, and other information)
    Elements overview = doc.getElementsByClass("realEstateLabel-3766429502");
    for (Element items : overview) {
      // Split the item. Since Kijiji fields are defined, we can use a switch
      // case to get each data field. The last element is the value.
      String field = items.text();
      String value = items.select("dd").text();
      rentalInfo.put(field, value);
    }
    // Sometimes number of bed and bath are not overview, but in another element
    if (rentalInfo.containsKey("Bathrooms") == false || rentalInfo.containsKey("Bedrooms") == false) {
      Elements bedBath = doc.getElementsByClass("attributeLabel-240934283");
      for (Element items : bedBath) {
        // Split the item. Since Kijiji fields are defined, we can use a switch
        // case to get each data field. The last element is the value.
        String field = items.text();
        String value = items.nextElementSibling().text();
        rentalInfo.put(field, value);
      }
    }
    // Extra utilities and features that are included (have a check mark on the listing)
    // are under the class groupItem-1102690470 available-731611581. Without will be under 
    // the class groupItem-1102690470, but without the available-731611581 suffix.
    Elements includedUtilities = doc.select("li[class=groupItem-1102690470 available-731611581]");
    for (Element items : includedUtilities) {
      String[] field = items.text().split(" ");
      String key = field[0];
      rentalInfo.put(key, "Included");
    }
    
    Elements notIncludeUtilities = doc.select("li[class=groupItem-1102690470]");
    for (Element items : notIncludeUtilities) {
      String[] field = items.text().split(" ");
      String key = field[0];
      rentalInfo.put(key, "Not included");
    }
    // Create the KijijiData object
    KijijiData kijijiData = createData(rentalInfo);
    // Send it to the database
    DatabaseSender dbSender = DatabaseSender.returnDatabaseSender();
    dbSender.sendToDatabase(kijijiData,"jdbc:sqlite:./Assignment3.sqlite");
  }

  private KijijiData createData(HashMap<String, String> rentalInfo) {
    String linkToListing = rentalInfo.get("Link To Listing").toString();
    String adTitle = rentalInfo.get("Ad Title").toString();
    String datePosted = rentalInfo.get("Date Posted").toString();
    String address = rentalInfo.get("Address").toString();
    String price = rentalInfo.get("Price").toString();
    // Optional parameters
    String unitType = "null";
    if (rentalInfo.get("Unit Type") != null)
      unitType = rentalInfo.get("Unit Type").toString();
    String numBedrooms = "null";
    if (rentalInfo.get("Bedrooms") != null)
      numBedrooms = rentalInfo.get("Bedrooms").toString();
    String numBathrooms = "null";
    if (rentalInfo.get("Bathrooms") != null)
      numBathrooms = rentalInfo.get("Bathrooms").toString();
    String petsAllowed = "null";
    if (rentalInfo.get("Pets Friendly") != null)
      petsAllowed = rentalInfo.get("Pets Friendly").toString();
    String parking = "null";
    if (rentalInfo.get("Parking Included") != null)
      parking = rentalInfo.get("Parking Included").toString();
    String size = "null";
    if (rentalInfo.get("Size (sqft)") != null)
      size = rentalInfo.get("Size (sqft)").toString();
    String smoking = "null";
    if (rentalInfo.get("Smoking Permitted") != null)
      smoking = rentalInfo.get("Smoking Permitted").toString();
    String furnished = "null";
    if (rentalInfo.get("Furnished") != null) 
      furnished =  rentalInfo.get("Furnished");
   String yard = "null";
    if (rentalInfo.get("Yard") != null) 
      yard =  rentalInfo.get("Yard");
   String balcony = "null";
    if (rentalInfo.get("Balcony") != null) 
      balcony =  rentalInfo.get("Balcony");
   String elevator = "null";
    if (rentalInfo.get("Elevator") != null) 
      elevator =  rentalInfo.get("Elevator");
   String hydro = "null";
    if (rentalInfo.get("Hydro") != null) 
      hydro =  rentalInfo.get("Hydro");
   String heating = "null";
    if (rentalInfo.get("Heating") != null) 
      heating =  rentalInfo.get("Heating");
   String water = "null";
    if (rentalInfo.get("Water") != null) 
      water =  rentalInfo.get("Water");
   String cable = "null";
    if (rentalInfo.get("Cable") != null)
      cable =  rentalInfo.get("Cable");
   String internet = "null";
    if (rentalInfo.get("Internet") != null) 
     internet =  rentalInfo.get("Internet");
   String landline = "null";
    if (rentalInfo.get("Landline") != null) 
      landline =  rentalInfo.get("Landline");
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
    return data;
  }

  
 public static void main(String[] args) throws IOException {
   String url = "https://www.kijiji.ca/v-house-for-sale/city-of-toronto/at-warden-lawrence-3-bed-bungalow-for-sale-call-today/1450882979";
   KijijiParser yea = KijijiParser.returnKijijiParserInstance();
   yea.parseThroughHTML(url);
 }
}

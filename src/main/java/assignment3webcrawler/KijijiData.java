package assignment3webcrawler;

import java.lang.reflect.Field;
import java.util.HashMap;

public class KijijiData {
  // Required
  private final String adTitle; 
  private final String address;
  private final String linkToListing;
  private final String datePosted;
  private final String price;
  // Optional.
  private String unitType;
  private String numBedrooms;
  private String numBathrooms;
  private String petsAllowed;
  private String parking;
  private String size;
  private String smoking;
  private String furnished;
  private String yard;
  private String balcony;
  private String elevator;
  private String hydro;
  private String heating;
  private String water;
  private String cable;
  private String internet;
  private String landline;

  public static class Builder {
    // Required 
    private final String adTitle; 
    private final String address;
    private final String linkToListing;
    private final String datePosted;
    private final String price;
    // Optional
    private String unitType;
    private String numBedrooms;
    private String numBathrooms;
    private String petsAllowed;
    private String parking = "null";
    private String size = "null";
    private String smoking = "null";
    private String furnished = "null";
    private String yard = "null";
    private String balcony = "null";
    private String elevator = "null";
    private String hydro = "null";
    private String heating = "null";
    private String water = "null";
    private String cable = "null";
    private String internet = "null";
    private String landline = "null";
    
    //Builder constructor with required fields (10)
    public Builder(String adTitle, String address, String linkToListing, String datePosted,
        String price) {
        //required parameters
        this.adTitle = adTitle;
        this.address = address;
        this.linkToListing = linkToListing;
        this.datePosted = datePosted;
        this.price = price;
    }
    
    
    // Optional parameters
    public Builder unitType(String val) {
      this.unitType = val;
      return this;
    }
    
    public Builder numBedrooms(String val) {
      this.numBedrooms = val;
      return this;
    }
    
    public Builder numBathrooms(String val) {
      this.numBathrooms = val;
      return this;
    }
    
    public Builder petsAllowed(String val) {
      this.petsAllowed = val;
      return this;
    }
    
    public Builder parking(String val) {
      this.parking = val;
      return this;
    }
    
    public Builder size(String val) {
      this.size = val;
      return this;
    }
    
    public Builder smoking(String val) {
      this.smoking = val;
      return this;
    }
    
    public Builder furnished(String val) {
      this.furnished = val;
      return this;
    }
    
    public Builder yard(String val) {
      this.yard = val;
      return this;
    }
    
    public Builder balcony(String val) {
      this.balcony = val;
      return this;
    }
    
    public Builder elevator(String val) {
      this.elevator = val;
      return this;
    }
    
    public Builder hydro(String val) {
      this.hydro = val;
      return this;
    }
    
    public Builder heating(String val) {
      this.heating = val;
      return this;
    }
    
    public Builder water(String val) {
      this.water = val;
      return this;
    }
    
    public Builder cable(String val) {
      this.cable = val;
      return this;
    }
    
    public Builder internet(String val) {
      this.internet = val;
      return this;
    }
    
    public Builder landline(String val) {
      this.landline = val;
      return this;
    }

    public KijijiData build() {
      return new KijijiData(this);
    }

  }
  
  
  public KijijiData(Builder b) {
    adTitle = b.adTitle; 
    address = b.address;
    linkToListing = b.linkToListing;
    datePosted = b.datePosted;
    unitType = b.unitType;
    numBedrooms = b.numBedrooms;
    numBathrooms = b.numBathrooms;
    petsAllowed = b.petsAllowed;
    parking = b.parking;
    size = b.size;
    price = b.price;
    smoking = b.smoking;
    furnished = b.furnished;
    yard = b.yard;
    balcony = b.balcony;
    elevator = b.elevator;
    hydro = b.hydro;
    heating = b.heating;
    water = b.water;
    cable = b.cable;
    internet = b.internet;
    landline = b.landline;
  }
  
 // Getters
  
  
  public String unitType() {
    return unitType;
  }
  
  public String numBedrooms() {
    return numBedrooms;
  }
  
  public String numBathrooms() {
    return numBathrooms;
  }
  
  public String petsAllowed() {
    return petsAllowed;
  }
  
  public String getParking() {
    return parking;
  }


  public String getSize() {
    return size;
  }


  public String getPrice() {
    return price;
  }



  public String getSmoking() {
    return smoking;
  }

  public String getFurnished() {
    return furnished;
  }


  public String getYard() {
    return yard;
  }


  public String getBalcony() {
    return balcony;
  }


  public String getElevator() {
    return elevator;
  }



  public String getHydro() {
    return hydro;
  }


  public String getHeating() {
    return heating;
  }



  public String getWater() {
    return water;
  }



  public String getCable() {
    return cable;
  }



  public String getInternet() {
    return internet;
  }


  public String getLandline() {
    return landline;
  }


  public String getAdTitle() {
    return adTitle;
  }


  public String getAddress() {
    return address;
  }

  public String getLinkToListing() {
    return linkToListing;
  }


  public String getDatePosted() {
    return datePosted;
  }


  public String getUnitType() {
    return unitType;
  }


  public String getNumBedrooms() {
    return numBedrooms;
  }


  public String getNumBathrooms() {
    return numBathrooms;
  }


  public String isPetsAllowed() {
    return petsAllowed;
  }
  
  
  public HashMap<String, Object> getAvailableData() {
    HashMap<String, Object> data = new HashMap<String, Object>();
    // Put all of the required values. They will be there
    data.put("adTitle", adTitle);
    data.put("address", address);
    data.put("linkToListing", linkToListing);
    data.put("datePosted", datePosted);
    data.put("price", price);
    // If any of the fields are their default values, don't add them to the map
    if (unitType != "null")
      data.put("unitType", unitType);
    if (numBedrooms != "null")
      data.put("numBedrooms", numBedrooms);
    if (numBathrooms != "null")
      data.put("numBathrooms",numBathrooms);
    if (petsAllowed != "null")
      data.put("petsAllowed", petsAllowed);
    if (parking != "null")
    	data.put("parking", parking);
    if (size != "null")
    	data.put("size", size);
    if (price != "null")
    	data.put("price", price);
    if (smoking != "null")
    	data.put("smoking", smoking);
    if (furnished != "null")
    	data.put("furnished", furnished);
    if (yard != "null")
    	data.put("yard", yard);
    if (balcony != "null")
    	data.put("balcony", balcony);
    if (elevator != "null")
    	data.put("elevator", elevator);
    if (hydro != "null")
    	data.put("hydro", hydro);
    if (heating != "null")
    	data.put("heating", heating);
    if (water != "null")
    	data.put("water", water);
    if (cable != "null")
    	data.put("cable", cable);
    if (internet != "null")
    	data.put("internet", internet);
    if (landline != "null")
    	data.put("landline", landline);
    return data;
  }
  
  @Override
  public String toString() {
  	// Return in a json format
  	String str = "{\n\t adTitle : \"" + adTitle 
  			+ "\",\n\t address : \"" + address  + "\",\n\t linkToListing : \"" 
  			+ linkToListing + "\",\n\t datePosted : \"" + datePosted + "\",\n\t unitType : \"" + unitType 
  			+ "\",\n\t numBedrooms : \"" + numBedrooms + "\",\n\t numBathrooms : \"" + numBathrooms 
  			+ "\",\n\t petsAllowed : \"" + petsAllowed + "\",\n\t parking : \"" + parking 
  			+ "\",\n\t size : \"" + size + "\",\n\t price : \"" + price + "\",\n\t smoking : \"" + smoking
  			+ "\",\n\t furnished : \"" + furnished + "\",\n\t yard : \"" + yard 
  			+ "\",\n\t balcony : \"" + balcony + "\",\n\t elevator : \"" + elevator 
  			+ "\",\n\t hydro : \"" + hydro + "\",\n\t heating : \"" + heating
  			+ "\",\n\t water : \"" + water + "\",\n\t cable : \"" + cable
  			+ "\",\n\t internet : \"" + internet + "\",\n\t landline : \"" + landline + "\"\n}";
  	return str;

  }
  
  
  public static void main(String args[]) {
    String si = "63";
  	KijijiData kd = new KijijiData.Builder("addTitle", "adddress",
        "l0nk2Listing", "datebosted", "money.com")
  	    .unitType("tree")
  	    .numBathrooms("1")
  	    .numBedrooms("24")
  	    .petsAllowed("can dog?")
        .parking("3")
        .size(si)
        .smoking("no smoke")
        .furnished("Yes")
        .yard("Yes")
        .balcony("No")
        .elevator("Yes")
        .hydro("Yes")
        .heating("No")
        .water("Yes")
        .cable("No")
        .internet("Yes")
        .landline("No")
        .build();
    System.out.println("size " + kd.getSize());
  	System.out.println(kd.toString());
  }
  

}
$(document).ready()
// This will be filled out at the beginning when outputMap is run. This will contain all of the locations,
// mapping address:{lat, lng, listing info}. lat, lng will be used to map, listing info will give a popup on the marker
// when clicked.
let listings;
let varToName = {"adTitle" : "Ad Title", "address" : "Address", "linkToListing" : "Link to Listing", "datePosted" : "Date Posted", "price" : "Price", "unitType" : "Unit Type", "numBathrooms" : "Bathrooms", "numBedrooms" : "Bedrooms", "petsAllowed" : "Pet-friendly", "parking" : "Parking Included", "size" : "Size (sqft)", "smoking" : "Smoking Permitted", "furnished" : "Furnished", "yard" : "Yard", "balcony" : "Balcony", "elevator" : "Elevator", "hydro" : "Hydro Included", "heating" : "Heating Included", "water" : "Water Included", "cable" : "Cable Included", "internet" : "Internet Included", "landline" : "Landline"}
$.getJSON("/mapMarkers.json", function(json) {
    listings = json;

    console.log(json); // this will show the info it in firebug console
});

function initMap() {
    console.log(listings); // this will show the info it in firebug console
    let map = new google.maps.Map(document.getElementById('map'), {
        zoom: 10,
        // Center on toronto
        center: {lat: 43.717899, lng: -79.6582408}
    });

    let marker;
    let i;
    
    for (i = 0; i < listings.length; i++) {  
        let obj = listings[i];
        let latLng = {lat: obj.lat, lng: obj.lng};
        console.log(latLng);
        marker = new google.maps.Marker({
            position: latLng,
            map: map,
            animation: google.maps.Animation.DROP
        });
        let features = '<ui>';
        for (var key in obj) {
            let conv = varToName[key];
            if (key == "linkToListing") {
                features += '<li><b>' + conv + '</b> : <a href=' + obj[key] + '>Click here to go to listing</a></li>\n';
            }
            else if (key != "lat" && key != "lng") {
                features += '<li><b>' + conv + '</b> : ' + obj[key] + '</li>\n';
            }
            
        }
        features += '</ui>';
        // Fill with content from KijijiData
        let contentString = '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h1 id="firstHeading" class="firstHeading">' + obj.adTitle + '</h1>'+
            '<div id="bodyContent">'+
            features +
            '</div>'+
            '</div>';
        console.log("no features: " + features);

            
        let infowindow = new google.maps.InfoWindow({
            content: contentString
        });
  

        google.maps.event.addListener(marker, 'click', (function(marker, i) {
            return function() {
            infowindow.open(map, marker);
            }
        })(marker, i));
    }
  }
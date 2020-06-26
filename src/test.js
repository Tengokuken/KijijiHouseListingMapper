$(document).ready()
// This will be filled out at the beginning when outputMap is run. This will contain all of the locations,
// mapping address:{lat, lng, listing info}. lat, lng will be used to map, listing info will give a popup on the marker
// when clicked.
let listings = [{lat: 43.7845948, lng: -79.1868269}, {lat: 43.717899, lng: -79.6582408}, {lat: 43.7849948, lng: -80.1868269}];
//let listings = [];



/* TODO: Need to continue testing after tomcat is set up
function init() {
    // Read from json file and populate listings
    let request = new XMLHttpRequest();
    let jsonFile = 'mapMarkers.json';


    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let contents = JSON.parse(this.responseText);
            console.log(contents)
            listings = contents;
        }
    };
    // TODO: On Firefox mathlab comps, had to download CORS Everywhere else you'd get 
    // Cross-Origin Request Blocked: The Same Origin Policy disallows reading the remote resource errors?
    // could be because i need to run on a server instead of opening the script, will look into this more
    request.open("GET", jsonFile, true);
    request.send();
}
*/

function initMap() {

    $.getJSON("mapMarkers.json", function(json) {
        console.log(json); // this will show the info it in firebug console
    });
    let utsc = {lat: 43.7845948, lng: -79.1868269};
    let map = new google.maps.Map(document.getElementById('map'), {
        zoom: 10,
        // Center on toronto
        center: {lat: 43.717899, lng: -79.6582408}
    });
    


  
    let marker;
    let i;
    
    for (i = 0; i < listings.length; i++) {  
        marker = new google.maps.Marker({
            position: listings[i],
            map: map,
            title: 'hi'
        });
        // Fill with content from KijijiData
        let contentString = '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h1 id="firstHeading" class="firstHeading">University of Toronto Scarborough</h1>'+
            '<div id="bodyContent">'+
            '<p><b>UTSC</b>, UTSC!.</p>' +
            '</div>'+
            '</div>';
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
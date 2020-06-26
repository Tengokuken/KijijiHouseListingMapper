Instructions:

1. Supply the tool with your Google Maps API Key in MapOutput.java. You can generate a key [here](https://developers.google.com/maps/documentation/javascript/get-api-key).

2. Run the following commands
	mvn clean install
	mvn tomcat7:run

3. Open the browser and go to the following link:
	http://localhost:8080/

4. On the page, enter the link to the Kijiji page(seed) in the first text box
 The crawler runs in the backend and will redirect the page to the map with
 postings displayed once it finishes processing the seed.
 
 	The link is required to be starting with: http//www.kijiji.ca
 	The number of posting is required to be a number that is greater than 0
 
 	If an invalid input is entered, the crawler will not run and no map will be shown.
 
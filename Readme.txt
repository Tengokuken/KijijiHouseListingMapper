Instructions:

1. Run the following commands
	mvn clean install
	mvn tomcat7:run

2. Open the browser and go to the following link:
	http://localhost:8080/

3. On the page, enter the link to the Kijiji page(seed) in the first text box
 and number of postings(limit) in the second box, then click the submit button.
 The crawler runs in the backend and will redirect the page to the map with
 postings displayed once it finishes processing the seed.
 
 	The link is required to be starting with: http//www.kijiji.ca
 	The number of posting is required to be a number that is greater than 0
 
 	If an invalid input is entered, the crawler will not run and no map will be shown.
 
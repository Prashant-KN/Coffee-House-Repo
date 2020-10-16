
# Prashant-CoffeeHouse-Solution

# Versions
	0.0.1-SNAPSHOT: contains all the below features.	
	
# Features

##	1) Customer
	  a) Customer can be identified by a name and phone number(fetch the customer by name or phone).
	  b) CRUD operations can be done on Customer.
	  
##	2) Coffee
	  a) Add new variety of coffee (each variety will have name, description, total Servings available for the day).
	  b) get coffee and delete coffee by ID. 
	  
##  3) Order
	  a) creates the order by taking customerId and list of coffeeId's and respective quantity, and saves the order.
	  b) Fetch the order info by order number.
	  c) Fetch report for the day which will have coffee types, total servings for the day,	total servings sold for the day.	  
	  
# Developers
	1) Prashant Kumar(prashantk@060@gmail.com)
	
# Running the appication
	1) unzip the project and extract it. the jar is already provided.
	2) using java -jar Prashant-CoffeeHouse-Solution-0.0.1-SNAPSHOT.jar
		will run the application.
	3) If you have maven setup in the system
		the application can be run using this command.
		"mvn install" a jar file will be generated.
	4) this jar can be run using java -jar command.
	
# SWAGGER UI
	1) I have added a swagger ui, where ReST API's can be tested.
	   http://localhost:8080/swagger-ui.html





# RestaurantMenu
Restaurant Menu REST API

mongoDB should be installed and run on local to run this app

mvn clean install -DskipTests and then run the app using command mvn spring-boot:run. We need to skip tests as there is no data initially in mongoDB and there are Live API Integration tests in the project. After populating data, we can run the tests.

REST API can be tested with any HTTP client.

localhost:8080/api/restaurants

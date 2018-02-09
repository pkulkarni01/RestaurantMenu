# RestaurantMenu
Restaurant Menu REST API

mongoDB should be installed and run on local to run this app

mvn clean install -DskipTests and then run the app using command mvn spring-boot:run. We need to skip tests as there is no data initially in mongoDB and there are Live API Integration tests in the project. After populating data, we can run the tests.

REST API can be tested with any HTTP client.

REST API endpoints

1) /api/restaurants -- GET, POST ---Gets and creates a restaruant
2) /api/restaurants/{id} -- GET restaurant with specific id and DELETE restaurant with specific id
3) /api/restaurants/{id}/menu -- GET , POST -- Gets and creates a restaurant menu.
4) /api/restaurants/{rid}/menu/{id} -- DELETE a restaurant menu with given id.
5) /api/restaurants/{rid}/menu/{id}/item -- POST,GET --- Gets and posts restaurant menu item.

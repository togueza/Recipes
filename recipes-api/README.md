# recipes-api
API to provide Recipes Comics data

This project was created from the Spring Initializr with H2 Database, Spring Web, Spring Data JPA, Spring Security and Spring Boot Dev Tools
The H2 Database was chosen to be easy for testing without the necessity of configuring a database.

Based on https://developer.recipes.com/docs#!/public

### Instructions to use
Download the zip project by clicking on Clone or download button, then Downlod ZIP;  
Extract the zip file and open a terminal in the folder extracted;  
Run the following command:  

If you have maven installed:
```
$ mvn spring-boot:run
```
If not:
```
$ java -jar recipes-api-0.0.1-SNAPSHOT.jar
```
Access http://localhost:8080/v1/recipes/ with user "test" and password "marvel" to see a list of recipes.

###API Documentation

http://localhost:8080/v2/api-docs  
http://localhost:8080/swagger-ui.html

### APIs endpoints
GET http://localhost:8080/v1/recipes/ [list all recipes]  
GET http://localhost:8080/v1/recipes/{id} [list a recipes by ID]  
GET http://localhost:8080/v1/recipes/findByName/{name} [list a recipes by name ignoring case]  
POST http://localhost:8080/v1/recipes/ [add a new recipes]  
PUT http://localhost:8080/v1/recipes/{id} [update recipes all attributes]  
PATCH http://localhost:8080/v1/recipes/{id} [update one or more attributes of a recipes]  
DELETE http://localhost:8080/v1/recipes/{id} [remove a recipes]  

# Lights-Out in browser game
Lights out game with Java 11, Springboot and Angular.
To build the projct use maven, and ng build for Angular.

It uses H2 in memory database. 
To connect to database use http://localhost:8080/h2-console/ username=sa, password=password

API documentation is provided at http://localhost:8080/swagger-ui/index.html
There is a postman collection available in the project under postman folder that can be imported into Postman
for testing APIs.

Game GUI is avalable on http://localhost:4200/

The application provides the ability to create a new Lights Out Game of size 3x3 to 8x8 and play it in the browser.
User can solve the game by using Get Solution button. A new game can be created using Get Problem. 
A game can be replayed by clicking Display all problems.
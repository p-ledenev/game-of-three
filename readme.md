# Game of three implementation details

The project providers server application and client application to run game of three.

## How to run

In project root directory run `mvn clean install`<br>

To start server application run 
```
java -jar server/target/server-bootstrap.jar
```
Host name and port are placed into **application.properties** 
file of **server** module

To start client application run 
```
java -jar client/target/client-bootstrap.jar
```
Server url to connect to is placed into **application.properties** 
file of **client** module

## Architecture

Game implemented via client-server architecture with Spring framework 
and Spring Boot with embedded Tomcat (for server side).

Server is stateless thus could serve concurrent clients queries.

Server presents its api through **server-api** module.

Server and client share game engine through **model** module.

Some unit tests for each module are implemented.

In case of network failure client just drops current game and 
suggests to start new one.

  
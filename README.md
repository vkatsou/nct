# NCT - Nutrition Calorie Tracker

## About this project
This project is intended to replace your paid daily nutrition tracker.😊

It covers all the basic request for a daily tracking activity.

The open source api used for this project is: https://api-ninjas.com/api/nutrition

The technology used for this application is:
* Springboot
* Maven
* Java 11
* Keycloak - openidconnect - oauth2
* MySQL
* Hibernate
* Mapstruct
* Lombok
* Swagger - OpenAPI

### Before you run the application
Go to https://api-ninjas.com/ and create an account to generate a new API-key.
Include that API key in the nct.ninja-api.api-key of application.yml properties.
Make sure you have a running keycloak instance and a mysql server.
(for any other relational database you have to configure the adapters)

The following commands are for arm based systems:
* Run the keycloak instance


        docker run -p 8080:8080 --name keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin koolwithk/keycloak-arm:16.0.0

* Run the MySQL instance


        docker run -d -p 3307:3307 --name mysql -e MYSQL_ROOT_PASSWORD=pass mysql/mysql-server:latest-aarch64


### Default swagger url:

    http://localhost:8180/swagger-ui/index.html
    
Enjoy!

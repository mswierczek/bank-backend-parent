## bank-backend
Simple implementation of the application based on Spring Boot for maintaining transactions and accounts in a bank

## Prerequisites
Following tools need to be installed and available:
* Unix/Linux
* JDK 19
* maven 3.8+
* screen (Unix tool)
## Build
Run `mvn clean install` on the `bank-backend-parent` level to build all required jars
## Execution
Run `sh run.sh` to start all services:
* account-service listens on port 8081
* transaction-service listens on port 8082
* each service is executed in a separate screen session

Run `sh stop.sh` to stop the services.
## Requests
Create new customer:
```
curl -v -H 'Content-Type: application/json' -H "Accept: application/json" -d '{"firstName": "Michael", "lastName": "Fox", "secondName": "Andrew", "birthDate": "1950-01-01"}' --request POST http://localhost:8081/account/customer
```
Create new account:
```
curl -v -H 'Content-Type: application/json' -H "Accept: application/json" -d '{"customerId": 1, "initialCredit": 0.00}' --request POST http://localhost:8081/account/create    
```
## Next steps
1. Add unit tests
2. Add integration tests
3. Add CI/CD handling
4. Transition Spring JPA repositories to Spring Data R2DBC 

## For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0/maven-plugin/reference/html/#build-image)
* [Spring Data JDBC](https://docs.spring.io/spring-boot/docs/3.0.0/reference/htmlsingle/#data.sql.jdbc)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.0/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


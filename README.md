# Manager Costumers
API to manage customers and their addresses.

Technologies
- Java 11
- Spark Framework
- Jackson
- Google Guice
- Flyway
- Gradle
- MySQL

### RUN on Ubuntu
Install MySQL (3306).
Change the info database in the class: br.com.compositebit.douglasgiordano.dao.ConfigDao

`gradle clean`

`gradle build -x test`

`gradle jar`

`java -jar build/libs/customer-manager-1.0-SNAPSHOT.jar`

### How to use?
The API consists of 10 paths based on the paths below.

localhost:8080/api/v1/customers
localhost:8080/api/v1/customers/{idCostumer}/address

### Documentation
The swagger was configured in the project. To access use the URL below.
https://app.swaggerhub.com/apis-docs/santosdealmeidaelias/customer-api/1.0.0#/



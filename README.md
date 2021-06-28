# Retail Price


### Technology and tools:

* Spring Boot 2.5.0
* Maven
* Spring Data JPA
* H2 database (Memory)
* Liquibase
* Swagger3
* Junit 5


### Test Application
The application start on port 8086. For test use these endpoint:
* Search price product : http://localhost:8086/api/price/detail/products/brandid/1/applydate/{applyDate}/productid/35455?applyDate=2020-06-14T10%3A00%3A00
* Pattern ApplyDate:  yyyy-MM-dd'T'HH:mm:ss

*  With Swagger we can  to see all endpoint: http://localhost:8086/swagger-ui.html

### Note: 
* Use the following url: http://localhost:8086/h2-console/ for it see  database in h2.
# Swagger-Spring Boot REST Demo


## Overview  
This demo's stub's were generated by the [swagger-codegen](https://github.com/swagger-api/swagger-codegen) project using the swagger.yaml file included in the source.  Thymeleaf and Bootstrap were used to generate the UI.  

The underlying library integrating swagger to SpringBoot is [springfox](https://github.com/springfox/springfox)  

After authentication you can view the swagger documentation via:

```
http://localhost:8080/docs
```

### Requirements

Java 1.8

Maven 3.x

### Building from source

```
mvn clean package
```

### Run the application

```
mvn spring-boot:run
```

Open browser to Login Page: 

http://localhost:8080/  




![login](/images/login.png)

For admin privileges (e.g. to delete an employee) user ***admin/admin*** credentials

Non-admin privileges use ***user/user***

Once authenticated, you are presented with the Employees page where you can perform various CRUD operations.



![employees](/images/employees.png)




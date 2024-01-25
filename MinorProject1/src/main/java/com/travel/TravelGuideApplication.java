package com.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//This is the main class for the TravelGuide application. It serves as the entry point for the application.
//It uses the Spring Boot framework and the `@SpringBootApplication` annotation to configure and run the application.
//The `main` method initializes the Spring application context and starts the application, allowing it to handle incoming requests and perform its functions.
@SpringBootApplication
public class TravelGuideApplication {

 public static void main(String[] args) {
     SpringApplication.run(TravelGuideApplication.class, args);
 }
}

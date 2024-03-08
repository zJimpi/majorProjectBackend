package com.travel.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
 // This class implements the WebMvcConfigurer interface to configure CORS settings.

 @Override
 public void addCorsMappings(CorsRegistry registry) {
     // Method to configure CORS mappings.

     registry.addMapping("/image/**")
         .allowedOrigins("http://localhost:4200")
         .allowedMethods("GET", "POST", "PUT", "DELETE")
         .allowedHeaders("*");
     // Configures CORS settings for paths starting with "/image/**".
     // It allows requests from "http://localhost:4200", permits specified HTTP methods, and allows any headers.

     registry.addMapping("/destination/**")
         .allowedOrigins("http://localhost:4200")
         .allowedMethods("GET", "POST", "PUT", "DELETE")
         .allowedHeaders("*");
     // Configures CORS settings for paths starting with "/destination/**".
     // It allows requests from "http://localhost:4200", permits specified HTTP methods, and allows any headers.
     
     registry.addMapping("/hotel/**")
     .allowedOrigins("http://localhost:4200")
     .allowedMethods("GET", "POST", "PUT", "DELETE")
     .allowedHeaders("*");
     
     registry.addMapping("/room/**")
     .allowedOrigins("http://localhost:4200")
     .allowedMethods("GET", "POST", "PUT", "DELETE")
     .allowedHeaders("*");
     
     registry.addMapping("/package/**")
     .allowedOrigins("http://localhost:4200")
     .allowedMethods("GET", "POST", "PUT", "DELETE")
     .allowedHeaders("*");
     
     registry.addMapping("/activity/**")
     .allowedOrigins("http://localhost:4200")
     .allowedMethods("GET", "POST", "PUT", "DELETE")
     .allowedHeaders("*");
     
     registry.addMapping("/bookingTable/**")
     .allowedOrigins("http://localhost:4200")
     .allowedMethods("GET", "POST", "PUT", "DELETE")
     .allowedHeaders("*");
     
     registry.addMapping("/carRent/**")
     .allowedOrigins("http://localhost:4200")
     .allowedMethods("GET", "POST", "PUT", "DELETE")
     .allowedHeaders("*"); 
     
     registry.addMapping("/carBooking/**")
     .allowedOrigins("http://localhost:4200")
     .allowedMethods("GET", "POST", "PUT", "DELETE")
     .allowedHeaders("*");
 }
}
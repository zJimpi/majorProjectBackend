package com.travel.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
//Lombok annotation that automatically generates getter methods for the class fields.

@Setter
//Lombok annotation that automatically generates setter methods for the class fields.

@AllArgsConstructor
//Lombok annotation that generates an all-args constructor for the class.

@NoArgsConstructor
//Lombok annotation that generates a no-args constructor for the class.

public class ErrorDetails {

 private Date timeStamp;
 // A field for storing the timestamp when the error occurred.

 private String message;
 // A field for storing a descriptive error message.

 private String details;
 // A field for storing additional details or context about the error.
}
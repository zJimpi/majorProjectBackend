package com.travel.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

 private Date timeStamp;
 // A field for storing the timestamp when the error occurred.

 private String message;
 // A field for storing a descriptive error message.

 private String details;
 // A field for storing additional details or context about the error.
}
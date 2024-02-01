package com.travel.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFound extends RuntimeException {

 private static final long serialVersionUID = 1L;
 // A unique identifier for the exception class.

 private String resourceName;
 // A field for storing the name of the resource that was not found.

 private String feildName;
 // A field for storing the name of the field that caused the resource not found exception.

 private Object feildValue;
 // A field for storing the value that caused the resource not found exception.

 public ResourceNotFound(String resourceName, String feildName, Object feildValue) {
     super(String.format("%s not found with %s : '%s'", resourceName, feildName, feildValue));
     // Constructs the exception with a formatted error message.

     this.resourceName = resourceName;
     this.feildName = feildName;
     this.feildValue = feildValue;
     // Sets the values of the fields based on the provided parameters.
 }
}
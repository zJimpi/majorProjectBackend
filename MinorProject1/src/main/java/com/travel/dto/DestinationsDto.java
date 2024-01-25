package com.travel.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
// These annotations are Lombok annotations, which automatically generate getter and setter methods for the class fields.

public class DestinationsDto {

    private int destId;
    // An integer field representing the unique identifier for a destination.

    @NotBlank(message = "Destination name is required")
    @Size(max = 50, message = "Max. limit is 50")
    private String destName;
    // A string field for the name of a destination. It is annotated with validation constraints.
    // It must not be blank and must have a maximum length of 50 characters.

    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Max. limit is 100")
    private String imageLocation;
    // A string field representing the location of an image. It is also annotated with validation constraints.
    // It must not be blank and has a maximum length of 100 characters.

    @Size(max = 150, message = "Max. limit is 150")
    private String imageDescription;
    // A string field for a description of the image. It has a maximum length of 150 characters but is not required.

    @NotBlank(message = "Destination type is required")
    @Size(max = 50, message = "Max. limit is 50")
    private String destType;
    // A string field for the type of destination. It is annotated with validation constraints.
    // It must not be blank and has a maximum length of 50 characters.

    private float popularityScore;
    // A floating-point field representing the popularity score of the destination.

    @NotBlank(message = "State or UT name is required")
    @Size(max = 60, message = "Max. limit is 60")
    private String stateAndUTName;
    // A string field for the state or union territory name. It is annotated with validation constraints.
    // It must not be blank and has a maximum length of 60 characters.

    private String imageFile;
    // A string field for the image file, which is not annotated with validation constraints.

}
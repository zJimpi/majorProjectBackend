package com.travel.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpotDto {

    private Long spotId;

    @NotBlank(message = "Spot Name is required")
    private String spotName;

    // Additional fields related to Spot entity, if any
}

package com.travel.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageDto {

    private Long pckgId;

    @NotBlank(message = "Package Name is required")
    private String pckgName;

    @NotBlank(message = "Package Code is required")
    private String packageCode;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Price is required")
    private Double price;
}

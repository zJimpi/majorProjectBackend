package com.travel.dto;

import java.util.ArrayList;
import java.util.List;

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

    // Additional fields for spots associated with the package (if needed)
    private ArrayList<String> spots;
}

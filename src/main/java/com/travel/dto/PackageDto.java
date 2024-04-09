package com.travel.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.travel.entity.Activity;

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
    
    private String packageDuration;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Price is required")
    private Double price;
    
    private String spots;
    
    private int maxNoOfBookings;
    
    private int noOfBookings;
    
    @OneToMany
	private List<Activity> activity;
    
    private String imageFile;
}

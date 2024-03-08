package com.travel.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.travel.entity.CarRentalDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarsDto {
	
	private int carId;
	
	private String carName;
	
	private int seats;
	
	private int bags;
	
	private String acOrNonAc;
	
	private float pricePerDay;
	
	@ManyToOne
	private CarRentalDetails carRentalDetails;

}
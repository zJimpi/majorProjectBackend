package com.travel.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarBookingsDto {
	
	private int carBookingId;
	
	
	private String username;
	
	
	private String pickUpDate;
	
	
	private String dropOffDate;
	
	
	private String location;
	
	
	private String acOrNonAc;
	
	
	private String carType;

}

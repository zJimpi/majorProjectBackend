package com.travel.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class bookingTableDto {
	
	
	private Long bookingId;
	
	@NotBlank(message = "User Name is required")
	private String userName;
	
	private Long userId;
	
	private Long packageId;
	
	private Long hotelId;
	
	private String[] roomTypes;
	
	private int[] NoOfRooms;
	
	private int NoOfAdults;
	
	private int NoOfChild;
	
	@NotBlank(message = "Date Range is required")
	private String dateRange;
	
	private Long totalPrice;

}

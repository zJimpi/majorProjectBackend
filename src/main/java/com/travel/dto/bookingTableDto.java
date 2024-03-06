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
	
	
	private String userName;
	
	
	private Long userId;
	
	
	private Long packageId;
	
	
	private Long  hotelId;
	
	
	private int[] roomIds;
	

	private int[] noOfRooms;
	
	
	private int adults;
	
	
	private int child;
	
	
	private String checkInDate;
	
	
	private String checkOutDate;
	
	
	private Long totalPrice;

}

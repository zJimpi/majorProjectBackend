package com.travel.dto;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
	
	private int reviewId;
	
	
	private String username;
	
	
	private String location;
	
	private String hotelName;
	
	private String packageName;
	
	
	private String comment;
	
	private int rating;

}
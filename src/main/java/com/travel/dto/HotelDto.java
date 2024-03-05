package com.travel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class HotelDto {


    private Long hotelId;


	@NotBlank(message = "Hotel name is required")
	@Size(max = 50, message = "Max. limit is 50")
	private String hotelName;
	
	private String location;

	private Long startingPrice;
    
    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Max. limit is 255")
    private String address;


	@Size(max = 20, message = "Max. limit is 20")
	private String number;

	private String imgUrl;

    @OneToMany
    private List<RoomDto> room;

}

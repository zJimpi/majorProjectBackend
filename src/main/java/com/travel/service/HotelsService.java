package com.travel.service;

import java.util.List;

import com.travel.dto.HotelDto;
import com.travel.entity.Hotel;

public interface HotelsService {

	HotelDto saveHotel(Hotel hotel);


    void deleteHotelById(Long hotelId);
    // Method to delete a hotel by its unique identifier (hotelId).


	void deleteHotelById(int hotelId);


	HotelDto getHotelById(Long hotelId);

	HotelDto updateHotel(Long hotelId, Hotel existingHotel);



}

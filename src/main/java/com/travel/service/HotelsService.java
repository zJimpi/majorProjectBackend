package com.travel.service;

import java.util.List;

import com.travel.dto.HotelDto;
import com.travel.entity.Hotel;

public interface HotelsService {

    HotelDto saveHotel(Hotel hotel);
    // Method to save a new hotel. It takes a 'Hotel' object and returns a 'HotelDto' representing the saved hotel.

    void deleteHotelById(Long hotelId);
    // Method to delete a hotel by its unique identifier (hotelId).

    List<HotelDto> getHotelList();
    // Method to retrieve a list of hotel data, typically as a list of 'HotelDto' objects.

	HotelDto getHotelById(Long hotelId);

	HotelDto updateHotel(Long hotelId, Hotel existingHotel);

	

}

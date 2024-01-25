package com.travel.service;

import java.util.List;

import com.travel.dto.HotelDto;
import com.travel.entity.Hotel;

public interface HotelsService {

    HotelDto saveHotel(Hotel hotel);
    // Method to save a new hotel. It takes a 'Hotel' object and returns a 'HotelDto' representing the saved hotel.

    void deleteHotelById(int hotelId);
    // Method to delete a hotel by its unique identifier (hotelId).

    HotelDto updateHotel(int hotelId, HotelDto existingHotelDto);
    // Method to update an existing hotel with the specified 'hotelId'. It takes a 'Hotel' object and returns a 'HotelDto' representing the updated hotel.

    List<HotelDto> getHotelList();
    // Method to retrieve a list of hotel data, typically as a list of 'HotelDto' objects.

	HotelDto getHotelById(int hotelId);

	HotelDto updateHotel(int hotelId, Hotel hotel);
}

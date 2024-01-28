package com.travel.service;

import java.util.List;

import com.travel.dto.HotelDto;
import com.travel.dto.RoomDto;
import com.travel.entity.Hotel;

public interface HotelsService {

    HotelDto saveHotel(Hotel hotel);
    // Method to save a new hotel. It takes a 'Hotel' object and returns a 'HotelDto' representing the saved hotel.

    void deleteHotelById(int hotelId);
    // Method to delete a hotel by its unique identifier (hotelId).

    List<HotelDto> getHotelList();
    // Method to retrieve a list of hotel data, typically as a list of 'HotelDto' objects.

	HotelDto getHotelById(int hotelId);

	HotelDto updateHotel(int hotelId, HotelDto existingHotelDto);

	RoomDto updateRoomForHotel(int hotelId, Long roomId, RoomDto roomDto);

	void deleteRoomForHotel(int hotelId, Long roomId);

	List<RoomDto> getRoomsForHotel(int hotelId);

	RoomDto saveRoomForHotel(int hotelId, RoomDto roomDto);

	HotelDto updateHotel(int hotelId, HotelDto existingHotelDto, List<RoomDto> roomsDto);

	HotelDto updateHotel(int hotelId, Hotel hotel);

}

package com.travel.service;

import java.util.List;

import com.travel.dto.HotelDto;
import com.travel.dto.RoomDto;
import com.travel.entity.Hotel;

public interface HotelsService {

	HotelDto saveHotel(Hotel hotel);
    void deleteHotelById(Long hotelId);
	HotelDto getHotelById(Long hotelId);
	HotelDto updateHotel(Long hotelId, Hotel existingHotel);
	public List<HotelDto> getHotelList();
	public void assignRoomidToHotelId(Long roomId,Long hotelId);
	void updateHotelRating(String hotelName);
	
	
}

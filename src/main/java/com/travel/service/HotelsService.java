package com.travel.service;

import java.util.List;

import com.travel.dto.HotelDto;
import com.travel.dto.RoomDto;
import com.travel.entity.Hotel;

public interface HotelsService {

	HotelDto saveHotel(Hotel hotel);

	HotelDto updateHotel(int hotelId, HotelDto existingHotelDto, List<RoomDto> roomsDto);

	void deleteHotelById(int hotelId);

	List<HotelDto> getHotelList();

	HotelDto getHotelById(int hotelId);

	RoomDto updateRoomForHotel(int hotelId, Long roomId, RoomDto roomDto);

	void deleteRoomForHotel(int hotelId, Long roomId);

	RoomDto saveRoomForHotel(int hotelId, RoomDto roomDto);

	List<RoomDto> getRoomsForHotel(int hotelId);



}

package com.travel.service;

import java.util.List;

import javax.validation.Valid;

import com.travel.dto.HotelDto;
import com.travel.dto.RoomDto;

public interface RoomService {

	RoomDto saveRoom(RoomDto roomDto);

	void deleteRoomById(Long roomId);

	RoomDto updateRoom(Long roomId, RoomDto roomDto);

	RoomDto getRoomById(Long roomId);


	List<RoomDto> getRoomList();

	List<RoomDto> updateRoomsForHotel(int hotelId, @Valid List<RoomDto> rooms);

}

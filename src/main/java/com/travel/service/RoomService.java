package com.travel.service;

import java.util.List;

import javax.validation.Valid;

import com.travel.dto.HotelDto;
import com.travel.dto.RoomDto;
import com.travel.entity.Room;

public interface RoomService {

	RoomDto saveRoom(RoomDto roomDto);

	void deleteRoomById(Long roomId);

	RoomDto updateRoomById(Long roomId, Room room);

	RoomDto getRoomById(Long roomId);


	List<RoomDto> getRoomList();

	public void assignRoomToHotel(Long roomId, Long hotelId);

}

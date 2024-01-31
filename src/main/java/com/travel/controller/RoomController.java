package com.travel.controller;

import com.travel.dto.RoomDto;
import com.travel.entity.Hotel;
import com.travel.entity.Room;
import com.travel.service.RoomService;
import com.travel.util.RoomConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {

    @Autowired
    private RoomService roomService;
    
    private RoomConverter roomConverter; 

    // Endpoint for saving a room
    @PostMapping("/saveRoom")
    public RoomDto saveRoom(@Valid @RequestBody RoomDto roomDto) {
        return roomService.saveRoom(roomDto);
    }

    // Endpoint for deleting a room by its ID
    @DeleteMapping("/deleteRoomById/{id}")
    public ResponseEntity<String> deleteRoomById(@PathVariable("id") Long roomId) {
        roomService.deleteRoomById(roomId);
        return new ResponseEntity<>(roomId + " is deleted successfully!!", HttpStatus.OK);
    }

    // Endpoint for updating a room by its ID
    @PutMapping("/updateRoomById/{id}")
    public RoomDto updateRoom(@PathVariable("id") Long roomId, @RequestBody RoomDto roomDto) {
    	final Room room = roomConverter.convertDtoToEntity(roomDto);
		return roomService.updateRoomById(roomId, room);
    }

    // Endpoint for retrieving a list of rooms
    @GetMapping("/getRoomList")
    public List<RoomDto> getRoomList() {
        return roomService.getRoomList();
    }

    // Endpoint for retrieving a room by its ID
    @GetMapping("/getRoomById/{id}")
    public RoomDto getRoomById(@PathVariable("id") Long roomId) {
        return roomService.getRoomById(roomId);
    }

    @PostMapping("/assignRoom/{roomId}/toHotel/{hotelId}")
    public ResponseEntity<String> assignRoomToHotel(@PathVariable("roomId") Long roomId,@PathVariable("hotelId") Long hotelId){
    	roomService.assignRoomToHotel(roomId, hotelId);
    	return new ResponseEntity<String>("Room with id "+roomId+" assinged to hotel with id "+hotelId, HttpStatus.OK);
    }

}

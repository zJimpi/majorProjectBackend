
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
    
    @Autowired
    private RoomConverter roomConverter; 


    @PostMapping("/saveRoom")
    public RoomDto saveRoom(@Valid @RequestBody RoomDto roomDto) {
        return roomService.saveRoom(roomDto);
    }

  
    @DeleteMapping("/deleteRoomById/{id}")
    public ResponseEntity<String> deleteRoomById(@PathVariable("id") Long roomId) {
        roomService.deleteRoomById(roomId);
        return new ResponseEntity<>(roomId + " is deleted successfully!!", HttpStatus.OK);
    }


    @PutMapping("/updateRoomById/{id}")
    public RoomDto updateRoom(@PathVariable("id") Long roomId, @RequestBody RoomDto roomDto) {
    	final Room room = roomConverter.convertDtoToEntity(roomDto);
		return roomService.updateRoomById(roomId, room);
    }

 
    @GetMapping("/getRoomList")
    public List<RoomDto> getRoomList() {
        return roomService.getRoomList();
    }

  
    @GetMapping("/getRoomById/{id}")
    public RoomDto getRoomById(@PathVariable("id") Long roomId) {
        return roomService.getRoomById(roomId);
    }


    @GetMapping("/getRoomByHotelId/{hotelId}")
    public List<RoomDto> getRoombyHotelId(@PathVariable("hotelId") Long hotelId) {
    
    	
    	return roomService.getroomByHotelId(hotelId);
    }

}


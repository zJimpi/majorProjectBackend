//package com.travel.controller;
//
//import com.travel.dto.RoomDto;
//import com.travel.service.RoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/room")
//@CrossOrigin(origins = "http://localhost:4200")
//public class RoomController {
//
//    @Autowired
//    private RoomService roomService;
//
//    // Endpoint for saving a room
//    @PostMapping("/saveRoom")
//    public RoomDto saveRoom(@Valid @RequestBody RoomDto roomDto) {
//        return roomService.saveRoom(roomDto);
//    }
//
//    // Endpoint for deleting a room by its ID
//    @DeleteMapping("/deleteRoomById/{id}")
//    public ResponseEntity<String> deleteRoomById(@PathVariable("id") Long roomId) {
//        roomService.deleteRoomById(roomId);
//        return new ResponseEntity<>(roomId + " is deleted successfully!!", HttpStatus.OK);
//    }
//
//    // Endpoint for updating a room by its ID
//    @PutMapping("/updateRoom/{id}")
//    public RoomDto updateRoom(@PathVariable("id") Long roomId, @RequestBody RoomDto roomDto) {
//        return roomService.updateRoom(roomId, roomDto);
//    }
//
//    // Endpoint for retrieving a list of rooms
//    @GetMapping("/getRoomList")
//    public List<RoomDto> getRoomList() {
//        return roomService.getRoomList();
//    }
//
//    // Endpoint for retrieving a room by its ID
//    @GetMapping("/getRoomById/{id}")
//    public RoomDto getRoomById(@PathVariable("id") Long roomId) {
//        return roomService.getRoomById(roomId);
//    }
//
//    // Endpoint for updating rooms for a hotel
//    @PutMapping("/updateRoomsForHotel/{hotelId}")
//    public List<RoomDto> updateRoomsForHotel(@PathVariable("hotelId") int hotelId, @Valid @RequestBody List<RoomDto> rooms) {
//        return roomService.updateRoomsForHotel(hotelId, rooms);
//    }
//}

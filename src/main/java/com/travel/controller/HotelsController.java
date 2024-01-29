package com.travel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.dto.HotelDto;
import com.travel.dto.RoomDto;
import com.travel.entity.Hotel;
import com.travel.service.HotelsService;
import com.travel.util.HotelsConverter;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = "http://localhost:4200")
public class HotelsController {

    @Autowired
    private HotelsService hotelsService;

    @Autowired
    private HotelsConverter hotelsConverter;

    // Endpoint for saving a hotel
    @PostMapping("/saveHotel")
    public HotelDto saveHotel(@Valid @RequestBody HotelDto hotelDto) {
        // Convert the DTO to an entity
        final Hotel hotel = hotelsConverter.convertDtoToEntity(hotelDto);

        // Call the service to save the hotel and return the result
        return hotelsService.saveHotel(hotel);
    }

    // Endpoint for deleting a hotel by its ID
    @DeleteMapping("/deleteHotelById/{id}")
    public ResponseEntity<String> deleteHotelById(@PathVariable("id") int hotelId) {
        // Call the service to delete the hotel by its ID
        hotelsService.deleteHotelById(hotelId);

        // Return a response indicating successful deletion
        return new ResponseEntity<>(hotelId + " is deleted successfully!!", HttpStatus.OK);
    }

    @PutMapping("/updateHotel/{id}")
    public ResponseEntity<?> updateHotel(@PathVariable("id") int hotelId, @RequestBody Map<String, Object> updates) {
        // Call the service to get the existing hotel by ID
        HotelDto existingHotelDto = hotelsService.getHotelById(hotelId);

        // Check if the hotel with the given ID exists
        if (existingHotelDto != null) {
            // Update the hotel entity with the provided key/value pairs
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                switch (key) {
                    case "hotelName":
                        existingHotelDto.setHotelName((String) value);
                        break;
                    case "hotelLocation":
                        existingHotelDto.setHotelLocation((String) value);
                        break;
                    case "address":
                        existingHotelDto.setAddress((String) value);
                        break;
                    case "hotelMobileNumber":
                        existingHotelDto.setHotelMobileNumber((String) value);
                        break;
                    case "managerName":
                        existingHotelDto.setManagerName((String) value);
                        break;
                    // Add more cases for other fields as needed
                }
            }

            // Call the service method to update the hotel
            try {
                hotelsService.updateHotel(hotelId, existingHotelDto, new ArrayList<>());
                return ResponseEntity.ok("Hotel updated successfully!");
            } catch (Exception e) {
                // Handle exceptions, return an appropriate response
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating hotel.");
            }
        } else {
            // Return a response indicating that the hotel with the given ID was not found
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint for retrieving a list of hotels
    @GetMapping("/getHotelList")
    public List<HotelDto> getHotelList() {
        // Call the service to get a list of hotels and return it
        return hotelsService.getHotelList();
    }

    @GetMapping("/getHotelById/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable("id") int hotelId) {
        // Call the service to get the hotel by its ID
        HotelDto hotelDto = hotelsService.getHotelById(hotelId);

        // Check if the hotel is found
        if (hotelDto != null) {
            // Return the hotel DTO with a status of OK
            return ResponseEntity.ok(hotelDto);
        } else {
            // Return a response indicating that the hotel with the given ID was not found
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint for updating rooms for a hotel
    @PutMapping("/updateRoomsForHotel/{hotelId}")
    public ResponseEntity<List<RoomDto>> updateRoomsForHotel(@PathVariable("hotelId") int hotelId,
            @RequestBody List<RoomDto> rooms) {

        // Call the service to check if the hotel with the given ID exists
        HotelDto existingHotelDto = hotelsService.getHotelById(hotelId);

        if (existingHotelDto != null) {
            // Update the hotel's rooms and return the updated rooms
            existingHotelDto.setRoom(rooms);
            hotelsService.updateHotel(hotelId, existingHotelDto, new ArrayList<>());

            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } else {
            // Handle the case where the hotel with the given ID does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or another appropriate response
        }
    }

    @DeleteMapping("/deleteRoomFromHotel/{hotelId}/{roomId}")
    public ResponseEntity<String> deleteRoomFromHotel(@PathVariable("hotelId") int hotelId,
            @PathVariable("roomId") Long roomId) {
        // Call the service to get the existing hotel by ID
        HotelDto existingHotelDto = hotelsService.getHotelById(hotelId);

        // Check if the hotel with the given ID exists
        if (existingHotelDto != null) {
            // Remove the room ID from the list of room IDs associated with the hotel
            existingHotelDto.getRoom().removeIf(roomDto -> roomDto.getRoomId().equals(roomId));

            // Call the service method to update the hotel
            try {
                // Provide an empty list of rooms as you don't need to update rooms here
                hotelsService.updateHotel(hotelId, existingHotelDto, new ArrayList<>());

                // Return a response indicating successful deletion
                return new ResponseEntity<>(roomId + " is deleted from hotel " + hotelId + " successfully!!",
                        HttpStatus.OK);
            } catch (Exception e) {
                // Handle exceptions, return an appropriate response
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating hotel.");
            }
        } else {
            // Return a response indicating that the hotel with the given ID was not found
            return ResponseEntity.notFound().build();
        }
    }
}

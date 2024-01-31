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

	@Autowired
	RoomController roomController;


	@PostMapping("/saveHotel")
	public HotelDto saveHotel(@Valid @RequestBody HotelDto hotelDto) {
	
		final Hotel hotel = hotelsConverter.convertDtoToEntity(hotelDto);
		return hotelsService.saveHotel(hotel);
	}


	@DeleteMapping("/deleteHotelById/{id}")
	public ResponseEntity<String> deleteHotelById(@PathVariable("id") Long hotelId) {
		
		hotelsService.deleteHotelById(hotelId);
		return new ResponseEntity<String>(hotelId + " is deleted successfully!!", HttpStatus.OK);
	}

	

	@GetMapping("/getHotelList")
	public List<HotelDto> getHotelList() {

		return hotelsService.getHotelList();
	}

	@GetMapping("/getHotelById/{id}")
	public HotelDto getHotelById(@PathVariable("id") Long hotelId) {
		return hotelsService.getHotelById(hotelId);
	}

	@PutMapping("/updateHotel/{id}")
	public HotelDto updateHotel(@PathVariable("id") Long hotelId, @RequestBody HotelDto hotelDto) {
		final Hotel hotel = hotelsConverter.convertDtoToEntity(hotelDto);
		return hotelsService.updateHotel(hotelId, hotel);
	}
}

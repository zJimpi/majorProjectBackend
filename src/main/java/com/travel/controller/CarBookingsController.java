package com.travel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.dto.CarBookingsDto;
import com.travel.service.CarBookingsService;
import com.travel.util.CarBookingsConverter;

@RestController
@RequestMapping("/carBooking")
@CrossOrigin(origins = "http://localhost:4200")
public class CarBookingsController {
	
	@Autowired
	CarBookingsService carBookingsService;
	
	@Autowired
	CarBookingsConverter carBookingsConverter;
	
	@PostMapping("/saveCarBooking")
	 public CarBookingsDto saveCarBooking(@Valid @RequestBody CarBookingsDto carBookingsDto) {
		 
		return carBookingsService.saveCarBooking(carBookingsDto);
	}
	
	@DeleteMapping("/deleteCarBookingById/{id}")
	public ResponseEntity<String> deleteCarBookingById(@PathVariable("id") int bookingId) {
		carBookingsService.deleteCarBookingById(bookingId);
	    return new ResponseEntity<>(bookingId + " is deleted successfully!!", HttpStatus.OK);
	}
	
	@GetMapping("/getCarBookingById/{id}")
	public CarBookingsDto getCarBookingById(@PathVariable("id") int bookingId) {
       return carBookingsService.getCarBookingById(bookingId);
   }
	
	@GetMapping("/getCarBookingListByUsername/{username}")
	    public List<CarBookingsDto> getCarBookingListByUsername(@PathVariable("bookingId") String username) {
	    return carBookingsService.getCarBookingByUsername(username);
	}

}

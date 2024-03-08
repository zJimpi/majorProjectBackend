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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.dto.bookingTableDto;
import com.travel.service.BookingTableService;
import com.travel.util.BookingTableConverter;

@RestController
@RequestMapping("/bookingTable")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingTableController {
	
	@Autowired
	BookingTableService bookingTableService;
	
	@Autowired
	BookingTableConverter bookingTableConverter;
	
	@PostMapping("/saveBookingTable")
	 public bookingTableDto saveBooking(@Valid @RequestBody bookingTableDto BookingTableDto) {
		 
		return bookingTableService.saveBookingTable(BookingTableDto);
	}
	
	@DeleteMapping("/deleteBookingById/{id}")
	public ResponseEntity<String> deleetBookingById(@PathVariable("id") Long bookingId) {
		bookingTableService.deleteBookingTableById(bookingId);
	    return new ResponseEntity<>(bookingId + " is deleted successfully!!", HttpStatus.OK);
	}
	
	@GetMapping("/getBookingById/{id}")
    public bookingTableDto getBookingById(@PathVariable("id") Long bookingId) {
        return bookingTableService.getBookingTableById(bookingId);
    }
	
	@GetMapping("/getBookingListByUsername/{username}")
	    public List<bookingTableDto> getBookingListByUsername(@PathVariable("bookingId") String username) {
	    return bookingTableService.getBookingTableByUsername(username);
	}
	    
	@PutMapping("/updatePriceByBookingId/{id}/{price}")
	    public void updatePriceByBookingId( @PathVariable("id") Long id,@PathVariable("price") Long price) {
		
	    	bookingTableService.updatePriceByBookingId(id,price);
	}
}
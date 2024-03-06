package com.travel.service;

import java.util.List;

import com.travel.dto.bookingTableDto;

public interface BookingTableService {
	
	bookingTableDto saveBookingTable(bookingTableDto BookingTableDto);
	
	void deleteBookingTableById(Long bookingId);
	
	bookingTableDto getBookingTableById(Long bookingId);
	
	List<bookingTableDto> getBookingTableByUsername(String Username);
	
	void updatePriceByBookingId(Long bookingId, Long price);

}
package com.travel.service;

import java.util.List;

import com.travel.dto.CarBookingsDto;

public interface CarBookingsService {
	
	CarBookingsDto saveCarBooking(CarBookingsDto carBookingsDto);
	
	void deleteCarBookingById(int bookingId);
	
	CarBookingsDto getCarBookingById(int bookingId);
	
	List<CarBookingsDto> getCarBookingByUsername(String Username);

}

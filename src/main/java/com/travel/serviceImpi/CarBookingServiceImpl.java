package com.travel.serviceImpi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dto.CarBookingsDto;
import com.travel.dto.bookingTableDto;
import com.travel.entity.BookingTable;
import com.travel.entity.CarBookings;
import com.travel.exception.ResourceNotFound;
import com.travel.repository.CarBookingsRepository;
import com.travel.service.CarBookingsService;
import com.travel.util.CarBookingsConverter;

@Service
public class CarBookingServiceImpl implements CarBookingsService {
	
	@Autowired
	CarBookingsRepository carBookingsRepository;
	
	@Autowired
	CarBookingsConverter carBookingsConverter;

	@Override
	public CarBookingsDto saveCarBooking(CarBookingsDto carBookingsDto) {
		// TODO Auto-generated method stub
		
		CarBookings carBookings = carBookingsConverter.convertDtoToEntity(carBookingsDto);
		carBookingsRepository.save(carBookings);
		return carBookingsConverter.convertEntityToDto(carBookings);
	}

	@Override
	public void deleteCarBookingById(int bookingId) {
		// TODO Auto-generated method stub
		CarBookings carBookings = carBookingsRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFound("Car Booking Details", "id", bookingId));
		carBookingsRepository.delete(carBookings);
		
	}

	@Override
	public CarBookingsDto getCarBookingById(int bookingId) {
		// TODO Auto-generated method stub
		CarBookings carBookings = carBookingsRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFound("Car Booking Details", "id", bookingId));
		return carBookingsConverter.convertEntityToDto(carBookings);
	}

	@Override
	public List<CarBookingsDto> getCarBookingByUsername(String Username) {
		// TODO Auto-generated method stub
		List<CarBookings> carBookingsList = carBookingsRepository.findCarBookingsByUsername(Username);
		List<CarBookingsDto> CarBookingsDtosList = new ArrayList<>();

		for (CarBookings carBookings : carBookingsList) {
			CarBookingsDto carBookingsDto = carBookingsConverter.convertEntityToDto(carBookings);
			CarBookingsDtosList.add(carBookingsDto);
		}
		
		return CarBookingsDtosList;
	}
}

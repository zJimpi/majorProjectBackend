package com.travel.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.CarBookingsDto;
import com.travel.dto.CarRentalDetailsDto;
import com.travel.entity.CarBookings;
import com.travel.entity.CarRentalDetails;

@Component
public class CarBookingsConverter {
	
	public CarBookings convertDtoToEntity(CarBookingsDto carBookingsDto) {
		
		CarBookings carBookings = new CarBookings();

		if (carBookingsDto != null) {
			BeanUtils.copyProperties(carBookingsDto, carBookings);
		}

		return carBookings;
	}

	// Converts Room Entity to RoomDto
	public CarBookingsDto convertEntityToDto(CarBookings carBookings) {
		
		CarBookingsDto carBookingsDto = new CarBookingsDto();

		if (carBookings != null) {
			BeanUtils.copyProperties(carBookings, carBookingsDto);
		}

		return carBookingsDto;
	}


}

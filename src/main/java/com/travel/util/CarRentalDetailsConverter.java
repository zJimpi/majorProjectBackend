package com.travel.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.ActivityDto;
import com.travel.dto.CarRentalDetailsDto;
import com.travel.entity.Activity;
import com.travel.entity.CarRentalDetails;

@Component
public class CarRentalDetailsConverter {
	
	public CarRentalDetails convertDtoToEntity(CarRentalDetailsDto carRentalDetailsDto) {
		
		CarRentalDetails carRentalDetails = new CarRentalDetails();

		if (carRentalDetailsDto != null) {
			BeanUtils.copyProperties(carRentalDetailsDto, carRentalDetails);
		}

		return carRentalDetails;
	}

	// Converts Room Entity to RoomDto
	public CarRentalDetailsDto convertEntityToDto(CarRentalDetails carRentalDetails) {
		
		CarRentalDetailsDto carRentalDetailsDto = new CarRentalDetailsDto();

		if (carRentalDetails != null) {
			BeanUtils.copyProperties(carRentalDetails, carRentalDetailsDto);
		}

		return carRentalDetailsDto;
	}

}

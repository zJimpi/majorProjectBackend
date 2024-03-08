package com.travel.service;

import java.util.List;

import com.travel.dto.CarRentalDetailsDto;
import com.travel.entity.CarRentalDetails;

public interface CarRentalDetailsService {
	
	CarRentalDetailsDto saveCarRentalDetails(CarRentalDetailsDto carRentalDetailsDto);
	
	void deleteCarRentaldetailsById(int DetailsId);
	
	CarRentalDetailsDto updateCarRentalDetailsById(int DetailsId, CarRentalDetails carRentalDetails);
	
	CarRentalDetailsDto getCarRetalDetailsById(int DetailsId);

	List<CarRentalDetailsDto> getCarRentalDetailsList();

}

package com.travel.serviceImpi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dto.ActivityDto;
import com.travel.dto.CarRentalDetailsDto;
import com.travel.entity.Activity;
import com.travel.entity.CarRentalDetails;
import com.travel.exception.ResourceNotFound;
import com.travel.repository.CarRentalDetailsRepository;
import com.travel.service.CarRentalDetailsService;
import com.travel.util.CarRentalDetailsConverter;

@Service
public class CarRentalDetailsServiceImpl implements CarRentalDetailsService {
	
	@Autowired
	CarRentalDetailsConverter carRentalDetailsConverter;
	
	@Autowired
	CarRentalDetailsRepository carRentalDetailsRepository;

	@Override
	public CarRentalDetailsDto saveCarRentalDetails(CarRentalDetailsDto carRentalDetailsDto) {
		// TODO Auto-generated method stub
		
		CarRentalDetails carRentalDetails = carRentalDetailsConverter.convertDtoToEntity(carRentalDetailsDto);
		carRentalDetailsRepository.save(carRentalDetails);
		return carRentalDetailsConverter.convertEntityToDto(carRentalDetails);
	}

	@Override
	public void deleteCarRentaldetailsById(int DetailsId) {
		// TODO Auto-generated method stub
		
		CarRentalDetails carRentalDetails = carRentalDetailsRepository.findById(DetailsId).orElseThrow(() -> new ResourceNotFound("Car Rental Details", "id", DetailsId));
		carRentalDetailsRepository.delete(carRentalDetails);
	}

	@Override
	public CarRentalDetailsDto updateCarRentalDetailsById(int DetailsId, CarRentalDetails carRentalDetails) {
		// TODO Auto-generated method stub
		
		CarRentalDetails existingCarRentalDetails = carRentalDetailsRepository.findById(DetailsId)
				.orElseThrow(() -> new ResourceNotFound("Car Rental Details", "id", DetailsId));

		existingCarRentalDetails.setLocation(carRentalDetails.getLocation());
		existingCarRentalDetails.setShopName(carRentalDetails.getShopName());
		existingCarRentalDetails.setAveragePrice(carRentalDetails.getAveragePrice());
		
		carRentalDetailsRepository.save(existingCarRentalDetails);

		return carRentalDetailsConverter.convertEntityToDto(existingCarRentalDetails);
	}

	@Override
	public CarRentalDetailsDto getCarRetalDetailsById(int DetailsId) {
		// TODO Auto-generated method stub
		
		CarRentalDetails carRentalDetails = carRentalDetailsRepository.findById(DetailsId).orElseThrow(
				()-> new ResourceNotFound("Car Rental Details", "id", DetailsId));

		return carRentalDetailsConverter.convertEntityToDto(carRentalDetails);
	}

	@Override
	public List<CarRentalDetailsDto> getCarRentalDetailsList() {
		// TODO Auto-generated method stub
		
		List<CarRentalDetails> CarRentalDetailsList = carRentalDetailsRepository.findAll();
		List<CarRentalDetailsDto> CarRentalDetailsDtoList = new ArrayList<>();

		for (CarRentalDetails carRentalDetails : CarRentalDetailsList) {
			CarRentalDetailsDto carRentalDetailsDto = carRentalDetailsConverter.convertEntityToDto(carRentalDetails);
			CarRentalDetailsDtoList.add(carRentalDetailsDto);
		}

		return CarRentalDetailsDtoList;
	}

}
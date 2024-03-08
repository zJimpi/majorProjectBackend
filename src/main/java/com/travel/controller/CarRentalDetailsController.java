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

import com.travel.dto.ActivityDto;
import com.travel.dto.CarRentalDetailsDto;
import com.travel.entity.Activity;
import com.travel.entity.CarRentalDetails;
import com.travel.service.CarRentalDetailsService;
import com.travel.util.CarRentalDetailsConverter;

@RestController
@RequestMapping("/carRent")
@CrossOrigin(origins = "http://localhost:4200")
public class CarRentalDetailsController {
	
	@Autowired
    private CarRentalDetailsService carRentalDetailsService;
	
	@Autowired
	private CarRentalDetailsConverter carRentalDetailsConverter;
	
	@PostMapping("/saveCarRentalDetails")
	public CarRentalDetailsDto saveCarRentalDetails(@Valid @RequestBody CarRentalDetailsDto carRentalDetailsDto) {
		return carRentalDetailsService.saveCarRentalDetails(carRentalDetailsDto);
	}
	
	 @DeleteMapping("/deleteCarRentalDetailsById/{id}")
	 public ResponseEntity<String> deleteCarRentalDetailsById(@PathVariable("id") int detailsId) {
		 carRentalDetailsService.deleteCarRentaldetailsById(detailsId);
	     return new ResponseEntity<>(detailsId + " is deleted successfully!!", HttpStatus.OK);
	 }


	    @PutMapping("/updateCarRentalDetailsById/{id}")
	    public CarRentalDetailsDto updateActivity(@PathVariable("id") int detailsId, @RequestBody CarRentalDetailsDto carRentalDetailsDto) {
	    	final CarRentalDetails carRentalDetails = carRentalDetailsConverter.convertDtoToEntity(carRentalDetailsDto);
			return carRentalDetailsService.updateCarRentalDetailsById(detailsId, carRentalDetails);
	    }

	 
	    @GetMapping("/getCarRentalDetailsList")
	    public List<CarRentalDetailsDto> getCarRentalDetailsList() {
	        return carRentalDetailsService.getCarRentalDetailsList();
	    }

	  
	    @GetMapping("/getCarRentalDetailsById/{id}")
	    public CarRentalDetailsDto getCarRentalDetailsById(@PathVariable("id") int detailsId) {
	        return carRentalDetailsService.getCarRetalDetailsById(detailsId);
	    }
}
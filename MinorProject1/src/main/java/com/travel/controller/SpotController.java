package com.travel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.dto.SpotDto;
import com.travel.service.SpotService;

@RestController
@RequestMapping("/spot")
@CrossOrigin(origins = "http://localhost:4200")
public class SpotController {

	@Autowired
	SpotService spotService; // Autowired service for managing spots

	// Endpoint for saving a spot
	@PostMapping("/saveSpot")
	public SpotDto saveSpot(@Valid @RequestBody SpotDto spotDto) {
		// Call the service to save the spot and return the result
		return spotService.saveSpot(spotDto);
	}

	// Endpoint for deleting a spot by its ID
	@DeleteMapping("/deleteSpotById/{id}")
	public ResponseEntity<String> deleteSpotById(@PathVariable("id") Long spotId) {
		// Call the service to delete the spot by its ID
		spotService.deleteSpotById(spotId);

		// Return a response indicating successful deletion
		return new ResponseEntity<>(spotId + " is deleted successfully!!", HttpStatus.OK);
	}

	// Endpoint for updating a spot by its ID
	@PutMapping("/updateSpot/{id}")
	public SpotDto updateSpot(@PathVariable("id") Long spotId, @RequestBody SpotDto spotDto) {
		// Call the service method to update the spot
		return spotService.updateSpot(spotId, spotDto);
	}

	// Endpoint for retrieving a list of spots
	@GetMapping("/getSpotList")
	public List<SpotDto> getSpotList() {
		// Call the service to get a list of spots and return it
		return spotService.getSpotList();
	}

	// Endpoint for retrieving a spot by its ID
	@GetMapping("/getSpotById/{id}")
	public SpotDto getSpotById(@PathVariable("id") Long spotId) {
		// Call the service to get a spot by its ID and return it
		return spotService.getSpotById(spotId);
	}

	@PutMapping("/updateSpotsForPackage/{packageId}")
	public List<SpotDto> updateSpotsForPackage(@PathVariable("packageId") Long packageId,
			@Valid @RequestBody List<SpotDto> spots) {
		// Call the service to update spots for the package
		return spotService.updateSpotsForPackage(packageId, spots);
	}

}

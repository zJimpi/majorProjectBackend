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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.dto.DestinationsDto;
import com.travel.dto.HotelDto;
import com.travel.dto.PackageDto;
import com.travel.entity.Destinations;
import com.travel.service.DestinationsService;
import com.travel.service.PackageService;
import com.travel.util.DestinationsConverter;

@RestController
@RequestMapping("/destination")
@CrossOrigin(origins = "http://localhost:4200")
public class DestinationsController {

    @Autowired
    DestinationsService destinationsService; // Autowired service for managing destinations

    @Autowired
    DestinationsConverter destinationsConverter; // Autowired converter for converting DTOs and entities
    
    @Autowired
    PackageService packageService;

    // Endpoint for saving a destination
    @PostMapping("/saveDest")
    public DestinationsDto saveDestination(@Valid @RequestBody DestinationsDto destinationsDto) {
        // Convert the DTO to an entity
        final Destinations destinations = destinationsConverter.convertDtoToEntity(destinationsDto);

        // Call the service to save the destination and return the result
        return destinationsService.saveDestinations(destinations);
    }

    // Endpoint for deleting a destination by its ID
    @DeleteMapping("/deleteDestinationById/{id}")
    public ResponseEntity<String> deleteDestinationById(@PathVariable("id") int destId) {
        // Call the service to delete the destination by its ID
        destinationsService.deleteDestinationById(destId);

        // Return a response indicating successful deletion
        return new ResponseEntity<String>(destId + " is deleted successfully!!", HttpStatus.OK);
    }

    // Endpoint for updating a destination by its ID
    @PutMapping("/updateDestination/{id}")
    public DestinationsDto updateDestination(@PathVariable("id") int destinationId, @Valid @RequestBody DestinationsDto destinationsDto) {
        // Convert the DTO to an entity
        final Destinations destinations = destinationsConverter.convertDtoToEntity(destinationsDto);

        // Call the service to update the destination by its ID and return the result
        return destinationsService.updateDestinations(destinationId, destinations);
    }

    // Endpoint for retrieving a list of destinations
    @GetMapping("/getDestinationList")
    public List<DestinationsDto> getDestinationList() {
        // Call the service to get a list of destinations and return it
        return destinationsService.getDestinationList();
    }
    
    @GetMapping("/getDestinationById/{id}")
    public DestinationsDto getDestinationById(@PathVariable("id") int destinationId) {
    	
    	return destinationsService.getDestinationById(destinationId);
    	
    }
    
    @GetMapping("/getPackageListByDestination/{state}/{location}")
    public List<PackageDto> getPackageListByDestination(@PathVariable String state, @PathVariable String location) {
        return destinationsService.getPackageListByDestination(state, location);
    }
    
    @GetMapping("/getHotelListByDestination/{state}/{location}")
    public List<HotelDto> getHotelListByDestination(@PathVariable String state, @PathVariable String location) {
        return destinationsService.getHotelListByDestination(state, location);
    }
}
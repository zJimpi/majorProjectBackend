package com.travel.serviceImpi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.exception.ResourceNotFound;
import com.travel.dto.DestinationsDto;
import com.travel.entity.Destinations;
import com.travel.repository.DestinationsRepository;
import com.travel.service.DestinationsService;
import com.travel.util.DestinationsConverter;

@Service
//Indicates that this class is a Spring service component.

public class DestinationsServiceImpi implements DestinationsService {

 
    // Injects dependencies using Spring's dependency injection.
	@Autowired
    DestinationsRepository destinationsRepository; // Repository for managing destination data.
	
	@Autowired
    DestinationsConverter destinationsConverter; // Converter for converting between entity and DTO.
	
	@Autowired
    FileDataServiceImpi fileDataServiceImpi; // Service for managing file data.

 @Override
 public DestinationsDto saveDestinations(Destinations destinations) {
     // Method to save a new destination.

     destinationsRepository.save(destinations);
     // Save the destination data using the repository.

     return destinationsConverter.convertEntityToDto(destinations);
     // Convert the saved entity to a DTO and return it.
 }

 @Override
 public DestinationsDto updateDestinations(int destId, Destinations destinations) {
     // Method to update an existing destination.

     Destinations exisDestinations = destinationsRepository.findById(destId).orElseThrow(() -> new ResourceNotFound("Destination", "id", destId));
     // Find the existing destination by its ID, or throw a ResourceNotFound exception if not found.

     exisDestinations.setDestName(destinations.getDestName());
     exisDestinations.setDestType(destinations.getDestType());
     exisDestinations.setImageDescription(destinations.getImageDescription());

     destinationsRepository.save(exisDestinations);
     // Update the existing destination in the repository.

     return destinationsConverter.convertEntityToDto(exisDestinations);
     // Convert the updated entity to a DTO and return it.
 }

 @Override
 public List<DestinationsDto> getDestinationList() {
     // Method to retrieve a list of destination data.

     List<Destinations> destinations = destinationsRepository.findAll();
     // Retrieve all destinations from the repository.

     List<DestinationsDto> destinationsDto = new ArrayList<>();
     // Create a list to store DTOs.

     for (Destinations d : destinations) {
         DestinationsDto destinationDto = destinationsConverter.convertEntityToDto(d);
         // Convert each entity to a DTO and add it to the list.
         destinationsDto.add(destinationDto);
     }

     return destinationsDto;
     // Return the list of destination DTOs.
 }

 @Override
 public void deleteDestinationById(int destId) {
     // Method to delete a destination by its ID.

     Destinations destination = destinationsRepository.findById(destId).orElseThrow(() -> new ResourceNotFound("Destination", "id", destId));
     // Find the destination by its ID, or throw a ResourceNotFound exception if not found.

     destinationsRepository.delete(destination);
     // Delete the destination from the repository.

     if (destinationsRepository.findById(destId).get().getImageFile() != null) {
         fileDataServiceImpi.imageDelete(destId);
     }
     // If the destination has an associated image file, call the imageDelete method in the FileDataService to delete it.
 }
}
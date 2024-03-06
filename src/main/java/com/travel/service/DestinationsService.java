package com.travel.service;

import java.util.List;

import com.travel.dto.DestinationsDto;
import com.travel.entity.Destinations;

public interface DestinationsService {

    DestinationsDto saveDestinations(Destinations destinations);
    // Method to save a new destination. It takes a 'Destinations' object and returns a 'DestinationsDto' representing the saved destination.

    void deleteDestinationById(int destId);
    // Method to delete a destination by its unique identifier (destId).

    DestinationsDto updateDestinations(int destId, Destinations destinations);
    // Method to update an existing destination with the specified 'destId'. It takes a 'Destinations' object and returns a 'DestinationsDto' representing the updated destination.

    List<DestinationsDto> getDestinationList();
    // Method to retrieve a list of destination data, typically as a list of 'DestinationsDto' objects.
    
    DestinationsDto getDestinationById(int destId);
}
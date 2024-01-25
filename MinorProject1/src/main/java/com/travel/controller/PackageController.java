package com.travel.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.dto.PackageDto;
import com.travel.dto.SpotDto;  
import com.travel.entity.Package;
import com.travel.service.PackageService;
import com.travel.util.PackageConverter;

@RestController
@RequestMapping("/package")
@CrossOrigin(origins = "http://localhost:4200")
public class PackageController {

    @Autowired
    PackageService packageService; // Autowired service for managing packages

    @Autowired
    PackageConverter packageConverter; // Autowired converter for converting DTOs and entities

    @Autowired
    SpotController spotController; // Autowire SpotController for managing spots

    // Endpoint for saving a package
    @PostMapping("/savePackage")
    public PackageDto savePackage(@Valid @RequestBody PackageDto packageDto) {
        // Convert the DTO to an entity
        final Package packageEntity = packageConverter.convertDtoToEntity(packageDto);

        // Call the service to save the package and return the result
        return packageService.savePackage(packageEntity);
    }

    // Endpoint for deleting a package by its ID
    @DeleteMapping("/deletePackageById/{id}")
    public ResponseEntity<String> deletePackageById(@PathVariable("id") Long packageId) {
        // Call the service to delete the package by its ID
        packageService.deletePackageById(packageId);

        // Return a response indicating successful deletion
        return new ResponseEntity<>(packageId + " is deleted successfully!!", HttpStatus.OK);
    }

    // Endpoint for updating a package by its ID
    @PutMapping("/updatePackage/{id}")
    public PackageDto updatePackage(@PathVariable("id") Long packageId, @RequestBody Map<String, Object> updates) {
        // Call the service to get the existing package by ID
        PackageDto existingPackageDto = packageService.getPackageById(packageId);

        // Check if the package with the given ID exists
        if (existingPackageDto != null) {
            // Update the package entity with the provided key/value pairs
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                switch (key) {
                    case "pckgName":
                        existingPackageDto.setPckgName((String) value);
                        break;
                    case "packageCode":
                        existingPackageDto.setPackageCode((String) value);
                        break;
                    case "location":
                        existingPackageDto.setLocation((String) value);
                        break;
                    case "price":
                        existingPackageDto.setPrice((Double) value);
                        break;
                    // Add more cases for other fields as needed
                }
            }

            // Call the service method to update the package
            return packageService.updatePackage(packageId, packageConverter.convertDtoToEntity(existingPackageDto));
        } else {
            // Handle the case where the package with the given ID does not exist
            // ...

            return null; // or throw an exception based on your design
        }
    }

    // Endpoint for retrieving a list of packages
    @GetMapping("/getPackageList")
    public List<PackageDto> getPackageList() {
        // Call the service to get a list of packages and return it
        return packageService.getPackageList();
    }

 // Endpoint for updating spots for a package
    @PutMapping("/updateSpotsForPackage/{packageId}")
    public ResponseEntity<List<SpotDto>> updateSpotsForPackage(
            @PathVariable("packageId") Long packageId,
            @RequestBody List<SpotDto> spots) {

        // Call the service to check if the package with the given ID exists
        PackageDto existingPackageDto = packageService.getPackageById(packageId);

        if (existingPackageDto != null) {
            // Call the SpotController to update spots for the package
            List<SpotDto> updatedSpots = spotController.updateSpotsForPackage(packageId, spots);

            // Update the package's spots and return the updated spots
            existingPackageDto.setSpots(updatedSpots);
            packageService.updatePackage(packageId, packageConverter.convertDtoToEntity(existingPackageDto));

            return new ResponseEntity<>(updatedSpots, HttpStatus.OK);
        } else {
            // Handle the case where the package with the given ID does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or another appropriate response
        }
    }



    // Endpoint for deleting a spot from a package
    @DeleteMapping("/deleteSpotFromPackage/{packageId}/{spotId}")
    public ResponseEntity<String> deleteSpotFromPackage(@PathVariable("packageId") Long packageId, @PathVariable("spotId") Long spotId) {
        // Call the SpotController to delete the spot
        spotController.deleteSpotById(spotId);

        // Call the service to get the existing package by ID
        PackageDto existingPackageDto = packageService.getPackageById(packageId);

        // Check if the package with the given ID exists
        if (existingPackageDto != null) {
            // Remove the spot ID from the list of spot IDs associated with the package
            existingPackageDto.getSpots().removeIf(spotDto -> spotDto.getSpotId().equals(spotId));

            // Call the service method to update the package
            packageService.updatePackage(packageId, packageConverter.convertDtoToEntity(existingPackageDto));

            // Return a response indicating successful deletion
            return new ResponseEntity<>(spotId + " is deleted from package " + packageId + " successfully!!", HttpStatus.OK);
        } else {
            // Handle the case where the package with the given ID does not exist
            // ...

            return null; // or throw an exception based on your design
        }
    }
}

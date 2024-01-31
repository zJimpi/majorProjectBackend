//package com.travel.serviceImpi;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.travel.dto.SpotDto;
//import com.travel.entity.Spot;
//import com.travel.exception.ResourceNotFound;
//import com.travel.repository.PackageRepository;
//import com.travel.repository.SpotRepository;
//import com.travel.service.SpotService;
//import com.travel.util.SpotConverter;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import javax.validation.Valid;
//
//@Service
//public class SpotServiceImpl implements SpotService {
//
//	@Autowired
//	private SpotRepository spotRepository;
//
//	@Autowired
//	private SpotConverter spotConverter;
//
//	@Autowired
//	private PackageRepository packageRepository;
//
//	@Override
//	public SpotDto saveSpot(SpotDto spotDto) {
//		// Convert the DTO to Entity
//		Spot spotEntity = spotConverter.convertDtoToEntity(spotDto);
//
//		// Save the spot entity
//		spotRepository.save(spotEntity);
//
//		// Convert and return the saved spot as a DTO
//		return spotConverter.convertEntityToDto(spotEntity);
//	}
//
//	@Override
//	public void deleteSpotById(Long spotId) {
//		Spot spotEntity = spotRepository.findById(spotId).orElseThrow(() -> new ResourceNotFound("Spot", "id", spotId));
//		spotRepository.delete(spotEntity);
//	}
//
//	@Override
//	public SpotDto updateSpot(Long spotId, SpotDto spotDto) {
//		Spot existingSpot = spotRepository.findById(spotId)
//				.orElseThrow(() -> new ResourceNotFound("Spot", "id", spotId));
//
//		existingSpot.setSpotName(spotDto.getSpotName());
//		// Set additional fields as needed
//
//		// Save the updated spot
//		spotRepository.save(existingSpot);
//
//		// Convert and return the updated spot as a DTO
//		return spotConverter.convertEntityToDto(existingSpot);
//	}
//
//	@Override
//	public List<SpotDto> getSpotList() {
//		List<Spot> spots = spotRepository.findAll();
//		List<SpotDto> spotDtos = new ArrayList<>();
//
//		for (Spot spot : spots) {
//			SpotDto spotDto = spotConverter.convertEntityToDto(spot);
//			spotDtos.add(spotDto);
//		}
//
//		return spotDtos;
//	}
//
//	@Override
//	public SpotDto getSpotById(Long spotId) {
//		// Call the repository or any necessary logic to get the spot by its ID
//		Optional<Spot> optionalSpot = spotRepository.findById(spotId);
//
//		// Check if the spot is found
//		if (optionalSpot.isPresent()) {
//			// Convert the retrieved spot entity to DTO
//			Spot spotEntity = optionalSpot.get();
//			return spotConverter.convertEntityToDto(spotEntity);
//		} else {
//			// Handle the case where the spot with the given ID does not exist
//			throw new ResourceNotFound("Spot", "id", spotId);
//		}
//	}
//	
//	@Override
//	@Transactional
//	public List<SpotDto> updateSpotsForPackage(Long packageId, @Valid List<SpotDto> spots) {
//	    // Retrieve the package entity
//	    com.travel.entity.Package travelPackage = packageRepository.findById(packageId)
//	            .orElseThrow(() -> new ResourceNotFound("Package", "id", packageId));
//
//	    List<SpotDto> updatedSpots = new ArrayList<>();
//
//	    try {
//	        // Begin a transaction
//	        for (SpotDto spotDto : spots) {
//	            if (spotDto.getSpotId() != null) {
//	                // Existing spot, update it
//	                Spot existingSpot = spotRepository.findById(spotDto.getSpotId())
//	                        .orElseThrow(() -> new ResourceNotFound("Spot", "id", spotDto.getSpotId()));
//
//	                existingSpot.setSpotName(spotDto.getSpotName());
//	                existingSpot.setTravelPackage(travelPackage);
//	                spotRepository.save(existingSpot);
//
//	                updatedSpots.add(spotConverter.convertEntityToDto(existingSpot));
//	            } else {
//	                // New spot, create and save it
//	                Spot newSpot = new Spot();
//	                newSpot.setSpotName(spotDto.getSpotName());
//	                newSpot.setTravelPackage(travelPackage);
//	                spotRepository.save(newSpot);
//
//	                updatedSpots.add(spotConverter.convertEntityToDto(newSpot));
//	            }
//	        }
//	        // Commit the transaction
//	    } catch (Exception e) {
//	        // Rollback the transaction in case of an exception
//	        throw new RuntimeException("Failed to update spots for the package.", e);
//	    }
//
//	    return updatedSpots;
//	}
//
//
//
//
//}

package com.travel.serviceImpi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.travel.dto.ActivityDto;
import com.travel.dto.HotelDto;
import com.travel.dto.PackageDto;
import com.travel.dto.RoomDto;
import com.travel.entity.Activity;
import com.travel.entity.Hotel;
import com.travel.entity.Package;
import com.travel.entity.Review;
import com.travel.entity.Room;
import com.travel.exception.ResourceNotFound;
import com.travel.repository.ActivityRepository;
import com.travel.repository.HotelsRepository;
import com.travel.repository.PackageRepository;
import com.travel.repository.ReviewReposiory;
import com.travel.service.ActivityService;
import com.travel.service.HotelsService;
import com.travel.service.PackageService;
import com.travel.util.ActivityConverter;
import com.travel.util.HotelsConverter;
import com.travel.util.PackageConverter;
import com.travel.util.RoomConverter;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepository packageRepository;
    
    @Autowired
    private HotelsRepository hotelsRepository;

    @Autowired
    private PackageConverter packageConverter;
    
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private ActivityConverter activityConverter;
    
    @Autowired
    private ActivityRepository activityRepository;
    
    @Autowired
    private HotelsConverter hotelsConverter;
    
    @Autowired
    private RoomConverter roomsConverter;
    
    @Autowired
	private ReviewReposiory reviewReposiory;

    @Override
    public PackageDto savePackage(Package packageEntity) {
        // Check if a package with the same package code already exists
        if (packageAlreadyExists(packageEntity.getPackageCode())) {
            throw new DataIntegrityViolationException("A package with the same package code already exists.");
        }

        // Initialize spots with an empty list if it is null
//        if (packageEntity.getSpots() == null) {
//            packageEntity.setSpots(new ArrayList<>());
//        }

        packageRepository.save(packageEntity);

        // Convert and return the saved package as a DTO
        return packageConverter.convertEntityToDto(packageEntity);
    }


    @Override
    public void deletePackageById(Long packageId) {
        Package packageEntity = packageRepository.findById(packageId).orElseThrow(() -> new ResourceNotFound("Package", "id", packageId));
        List<ActivityDto>activityList = activityService.getActivityListByPackageId(packageId);
		for(ActivityDto a :activityList) {
			Activity activity = activityConverter.convertDtoToEntity(a);
			activityRepository.delete(activity);
		}
        
        packageRepository.delete(packageEntity);
    }

    @Override
    public PackageDto updatePackage(Long packageId, Package packageEntity) {
        Package existingPackage = packageRepository.findById(packageId).orElseThrow(() -> new ResourceNotFound("Package", "id", packageId));

        existingPackage.setPckgName(packageEntity.getPckgName());
        existingPackage.setPackageCode(packageEntity.getPackageCode());
        existingPackage.setPackageDuration(packageEntity.getPackageDuration());
        existingPackage.setLocation(packageEntity.getLocation());
        existingPackage.setPrice(packageEntity.getPrice());
        existingPackage.setSpots(packageEntity.getSpots());
        existingPackage.setMaxNoOfBookings(packageEntity.getMaxNoOfBookings());
        existingPackage.setNoOfBookings(packageEntity.getNoOfBookings());

        // Check if a package with the same package code already exists
        if (packageAlreadyExists(existingPackage.getPackageCode(), packageId)) {
            throw new DataIntegrityViolationException("A package with the same package code already exists.");
        }

        // Save the updated package if the check passes
        packageRepository.save(existingPackage);

        // Convert and return the updated package as a DTO
        return packageConverter.convertEntityToDto(existingPackage);
    }

    @Override
    public List<PackageDto> getPackageList() {
        List<Package> packages = packageRepository.findAll();
        List<PackageDto> packageDtos = new ArrayList<>();

        for (Package p : packages) {
            PackageDto packageDto = packageConverter.convertEntityToDto(p);
            packageDtos.add(packageDto);
        }

        return packageDtos;
    }

 // Additional method to check if a package with the same package code already exists
    private boolean packageAlreadyExists(String packageCode) {
        Package existingPackage = packageRepository.findByPackageCode(packageCode);
        return existingPackage != null;
    }



    // Additional method to check if a package with the same package code already exists excluding a specific packageId
    private boolean packageAlreadyExists(String packageCode, Long excludePackageId) {
        return packageRepository.findByPackageCodeAndPckgIdNot(packageCode, excludePackageId).isPresent();
    }

    @Override
    public PackageDto getPackageById(Long packageId) {
        // Call the repository or any necessary logic to get the package by its ID
        Optional<Package> optionalPackage = packageRepository.findById(packageId);

        // Check if the package is found
        if (optionalPackage.isPresent()) {
            // Convert the retrieved package entity to DTO
            Package packageEntity = optionalPackage.get();
            return packageConverter.convertEntityToDto(packageEntity);
        } else {
            // Handle the case where the package with the given ID does not exist
            throw new ResourceNotFound("Package", "id", packageId);
        }
    }
    
    @Override
    public String getSpotsByPackageId(Long packageId)
    {
    	Package packageEntity = packageRepository.findById(packageId).orElseThrow(() -> new ResourceNotFound("Package", "id", packageId));
    	return packageEntity.getSpots();
    }


    @Override
    public List<HotelDto> getHotelListByPackageLocation(String packageLocation) {
        List<Hotel> allHotels = hotelsRepository.findAllHotelsWithRooms();
        List<HotelDto> filteredHotelDtos = new ArrayList<>();

        for (Hotel hotel : allHotels) {
            String hotelLocation = hotel.getLocation().toLowerCase();
            String packageLocationLower = packageLocation.toLowerCase();

            if (hotelLocation.contains(packageLocationLower) || packageLocationLower.contains(hotelLocation)) {
                // Convert Hotel entity back to HotelDto
                HotelDto hotelDto = hotelsConverter.convertEntityToDto(hotel);
                
                // Convert rooms to RoomDto
                List<RoomDto> roomDtos = new ArrayList<>();
                for (Room room : hotel.getRoom()) {
                    RoomDto roomDto = roomsConverter.convertEntityToDto(room);
                    roomDtos.add(roomDto);
                }
                
                // Set the rooms in the hotelDto
                hotelDto.setRoom(roomDtos);
                
                filteredHotelDtos.add(hotelDto);
            }
        }

        return filteredHotelDtos;
    }
    
    @Override
    public void updatePackageRating(String packageName)
	{
		List<Review> reviews = reviewReposiory.findReviewsByPackageName(packageName);
		int totalRating = 0;
		for(Review review : reviews) {
			totalRating = totalRating + review.getRating();
		}
		int averageRating = (int) Math.floor((double) totalRating / reviews.size());
		Package packageEntity = packageRepository.findPackageByName(packageName);
		packageEntity.setRating(averageRating);
		
		packageRepository.save(packageEntity);
		
	}
}
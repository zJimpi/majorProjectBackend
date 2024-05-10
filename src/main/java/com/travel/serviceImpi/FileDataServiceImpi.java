package com.travel.serviceImpi;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travel.entity.Activity;
import com.travel.entity.CarRentalDetails;
import com.travel.entity.Destinations;
import com.travel.entity.Package;
import com.travel.entity.Room;
import com.travel.entity.User;
import com.travel.entity.FileData;
import com.travel.entity.Hotel;
import com.travel.exception.ResourceNotFound;
import com.travel.repository.ActivityRepository;
import com.travel.repository.CarRentalDetailsRepository;
import com.travel.repository.DestinationsRepository;
import com.travel.repository.FileDataRepository;
import com.travel.repository.HotelsRepository;
import com.travel.repository.PackageRepository;
import com.travel.repository.RoomRepository;
import com.travel.repository.UserRepository;
import java.io.File;


@Service
public class FileDataServiceImpi {

	@Autowired
	private FileDataRepository fileDataRepository;

	@Autowired
	private DestinationsRepository destinationsRepository;
 
	@Autowired
	private PackageRepository packageRepository;
 
	@Autowired
	private ActivityRepository activityRepository;
 
	@Autowired
	private RoomRepository roomRepository;
 
	@Autowired
	private UserRepository userRepository;
 
	@Autowired
	private HotelsRepository hotelsRepository;
	
	@Autowired
	private CarRentalDetailsRepository carRentalDetailsRepository;
 
 
	private final String globalPath = "S:/minor_project/majorproject/fontentcopy";
 

	private final String destFOLDER_PATH = globalPath + "/src/assets/destImage/";
	private final String packageFOLDER_PATH = globalPath + "/src/assets/pkg_img/";
	private final String activityFOLDER_PATH = globalPath + "/src/assets/activityImage/";
	private final String roomFOLDER_PATH = globalPath + "/src/assets/roomImage/";
	private final String hotelFOLDER_PATH = globalPath + "/src/assets/hotel_image/";
	private final String userFOLDER_PATH = globalPath + "/src/assets/userImage/";
	private final String carRentLocationFOLDER_PATH = globalPath +"/src/assets/carRentLocationImage/";

	
 
	public String uploadImageToDestinationFileSystem(MultipartFile file, int id) throws IOException {
    
		String filePath = destFOLDER_PATH + file.getOriginalFilename();
		FileData fileData = fileDataRepository.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filePath)
				.build());
    
		file.transferTo(new File(filePath));
		if (fileData != null) {
        
			String destName = file.getOriginalFilename();
			Destinations destination = destinationsRepository.findById(id).orElseThrow();
			destination.setImageFile("assets/destImage/" + destName);
			destinationsRepository.save(destination);
			return "File uploaded successfully: " + filePath;
		}
		return null;
	}
	
	public String uploadImageToPackageFileSystem(MultipartFile file, Long id) throws IOException {
	    
		String filePath = packageFOLDER_PATH + file.getOriginalFilename();
		FileData fileData = fileDataRepository.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filePath)
				.build());
    
		file.transferTo(new File(filePath));
		if (fileData != null) {
        
			String packageName = file.getOriginalFilename();
			Package packageEntity = packageRepository.findById(id).orElseThrow();
			packageEntity.setImageFile("assets/pkg_img/" + packageName);
			packageRepository.save(packageEntity);
			return "File uploaded successfully: " + filePath;
		}
		return null;
	}
	
	public String uploadImageToActivityFileSystem(MultipartFile file, Long id) throws IOException {
	    
		String filePath = activityFOLDER_PATH + file.getOriginalFilename();
		FileData fileData = fileDataRepository.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filePath)
				.build());
    
		file.transferTo(new File(filePath));
		if (fileData != null) {
        
			String activityName = file.getOriginalFilename();
			Activity activity = activityRepository.findById(id).orElseThrow();
			activity.setImageFile("assets/activityImage/" + activityName);
			activityRepository.save(activity);
			return "File uploaded successfully: " + filePath;
		}
		return null;
	}
	
	public String uploadImageToHotelFileSystem(MultipartFile file, Long id) throws IOException {
	    
		String filePath = hotelFOLDER_PATH + file.getOriginalFilename();
		FileData fileData = fileDataRepository.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filePath)
				.build());
    
		file.transferTo(new File(filePath));
		if (fileData != null) {
        
			String hotelName = file.getOriginalFilename();
			Hotel hotel = hotelsRepository.findById(id).orElseThrow();
			hotel.setImageFile("assets/hotel_image/" + hotelName);
			hotelsRepository.save(hotel);
			return "File uploaded successfully: " + filePath;
		}
		return null;
	}
	
	public String uploadImageToRoomFileSystem(MultipartFile file, Long id) throws IOException {
	    
		String filePath = roomFOLDER_PATH + file.getOriginalFilename();
		FileData fileData = fileDataRepository.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filePath)
				.build());
    
		file.transferTo(new File(filePath));
		if (fileData != null) {
        
			String roomName = file.getOriginalFilename();
			Room room = roomRepository.findById(id).orElseThrow();
			room.setImageFile("assets/roomImage/" + roomName);
			roomRepository.save(room);
			return "File uploaded successfully: " + filePath;
		}
		return null;
	}
	
	public String uploadImageToUserFileSystem(MultipartFile file, Long id) throws IOException {
	    
		String filePath = userFOLDER_PATH + file.getOriginalFilename();
		FileData fileData = fileDataRepository.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filePath)
				.build());
    
		file.transferTo(new File(filePath));
		if (fileData != null) {
        
			String userName = file.getOriginalFilename();
			User user = userRepository.findById(id).orElseThrow();
			user.setImageFile("assets/userImage/" + userName);
			userRepository.save(user);
			return "File uploaded successfully: " + filePath;
		}
		return null; 
	}
	
	public String uploadImageToCarRentLocationFileSystem(MultipartFile file, int id) throws IOException {
	    
		String filePath = carRentLocationFOLDER_PATH + file.getOriginalFilename();
		FileData fileData = fileDataRepository.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filePath)
				.build());
    
		file.transferTo(new File(filePath));
		if (fileData != null) {
        
			String carRentLocationName = file.getOriginalFilename();
			CarRentalDetails carRentalDetails = carRentalDetailsRepository.findById(id).orElseThrow();
			carRentalDetails.setImageFile("assets/carRentLocationImage/" + carRentLocationName);
			carRentalDetailsRepository.save(carRentalDetails);
			return "File uploaded successfully: " + filePath;
		}
		return null; 
	}
	
	public void imageDelete(int id) {
		FileData fileData = fileDataRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFound("Image", "id", id));

		fileDataRepository.delete(fileData);
	}
}
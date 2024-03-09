package com.travel.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travel.serviceImpi.FileDataServiceImpi;

@RestController 
@RequestMapping("/image") 
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class FileDataController {

	@Autowired
	FileDataServiceImpi serviceImpi; 

	@PostMapping("/destinationfileSystem/{destId}")
	public ResponseEntity<?> uploadImageToDestinationFileSystem(@RequestParam("imageFile") MultipartFile file, @PathVariable("destId") int destId) throws IOException {
		String uploadImage = serviceImpi.uploadImageToDestinationFileSystem(file,destId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}
	
	@PostMapping("/packagefileSystem/{packageId}")
	public ResponseEntity<?> uploadImageToPackageFileSystem(@RequestParam("imageFile") MultipartFile file, @PathVariable("packageId") Long packageId) throws IOException {
		String uploadImage = serviceImpi.uploadImageToPackageFileSystem(file,packageId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}
	
	@PostMapping("/hotelfileSystem/{hotelId}")
	public ResponseEntity<?> uploadImageToHotelFileSystem(@RequestParam("imageFile") MultipartFile file, @PathVariable("hotelId") Long hotelId) throws IOException {
		String uploadImage = serviceImpi.uploadImageToHotelFileSystem(file,hotelId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}
	
	@PostMapping("/activityfileSystem/{activityId}")
	public ResponseEntity<?> uploadImageToActivityFileSystem(@RequestParam("imageFile") MultipartFile file, @PathVariable("activityId") Long activityId) throws IOException {
		String uploadImage = serviceImpi.uploadImageToActivityFileSystem(file,activityId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}
	
	@PostMapping("/roomfileSystem/{roomId}")
	public ResponseEntity<?> uploadImageToRoomFileSystem(@RequestParam("imageFile") MultipartFile file, @PathVariable("roomId") Long roomId) throws IOException {
		String uploadImage = serviceImpi.uploadImageToRoomFileSystem(file,roomId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}
	
	@PostMapping("/userfileSystem/{userId}")
	public ResponseEntity<?> uploadImageToUserFileSystem(@RequestParam("imageFile") MultipartFile file, @PathVariable("userId") Long userId) throws IOException {
		String uploadImage = serviceImpi.uploadImageToUserFileSystem(file,userId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}
	
	@PostMapping("/carRentLocationfileSystem/{carLocationId}")
	public ResponseEntity<?> uploadImageToCarRentLocationFileSystem(@RequestParam("imageFile") MultipartFile file, @PathVariable("carLocationId") int carLocationId) throws IOException {
		String uploadImage = serviceImpi.uploadImageToCarRentLocationFileSystem(file,carLocationId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	
//	@GetMapping("/fileSystem/{fileName}")
//	// Defines an HTTP GET endpoint for downloading an image from the file system.
//	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
//		// This method takes the 'fileName' from the URL path and calls the 'downloadImageFromFileSystem' method from the 'service'.
//		byte[] imageData = serviceImpi.downloadImageFromFileSystem(fileName);
//		// Returns a ResponseEntity with HTTP status OK (200) and sets the response content type to "image/png".
//		// The image data is set as the response body, which should be a binary image.
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("image/png"))
//				.body(imageData);
//	}
}
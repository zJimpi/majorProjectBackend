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

@RestController // Indicates that this class is a Spring MVC Controller, and it handles HTTP requests.
@RequestMapping("/image") // Defines the base path for this controller's endpoints (e.g., "/image").
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
// Allows Cross-Origin Resource Sharing (CORS) requests from "http://localhost:4200" with a maximum age of 3600 seconds.
public class FileDataController {

	@Autowired
	FileDataServiceImpi serviceImpi; // Injects an instance of the FileDataService to interact with the file system.

	@PostMapping("/fileSystem")
	// Defines an HTTP POST endpoint for uploading an image to the file system.
	public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("imageFile") MultipartFile file) throws IOException {
		// This method receives a multipart file as a parameter, typically an image file.
		// It then calls the 'uploadImageToFileSystem' method from the 'service' to store the image.
		String uploadImage = serviceImpi.uploadImageToFileSystem(file);
		// Returns a ResponseEntity with HTTP status OK (200) and the uploaded image's filename or path as the response body.
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/fileSystem/{fileName}")
	// Defines an HTTP GET endpoint for downloading an image from the file system.
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		// This method takes the 'fileName' from the URL path and calls the 'downloadImageFromFileSystem' method from the 'service'.
		byte[] imageData = serviceImpi.downloadImageFromFileSystem(fileName);
		// Returns a ResponseEntity with HTTP status OK (200) and sets the response content type to "image/png".
		// The image data is set as the response body, which should be a binary image.
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);
	}
}
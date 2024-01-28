package com.travel.serviceImpi;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travel.entity.Destinations;
import com.travel.entity.FileData;
import com.travel.exception.ResourceNotFound;
import com.travel.repository.DestinationsRepository;
import com.travel.repository.FileDataRepository;
import com.travel.service.FileDataService;

import java.io.File;
import java.util.Optional;
import java.nio.file.Files;

@Service
//Indicates that this class is a Spring service component.

public class FileDataServiceImpi implements FileDataService {

 @Autowired
 private FileDataRepository fileDataRepository;
 // Injects a repository for managing file data.

 @Autowired
 private DestinationsRepository destinationsRepository;
 // Injects a repository for managing destination data.

 // private ImageDataComDecom imageDataComDecom;
 // It seems to be a commented-out field for another functionality or dependency.

 private final String FOLDER_PATH = "C:/WanderQuest/fontentcopy/src/assets/images";
 // Specifies the folder path where uploaded images will be stored.
 
 @Override
 public String uploadImageToFileSystem(MultipartFile file) throws IOException {
     // Method to upload an image to the file system.

     String filePath = FOLDER_PATH + file.getOriginalFilename();
     // Create the full file path by combining the folder path and the original file name.

     FileData fileData = fileDataRepository.save(FileData.builder()
         .name(file.getOriginalFilename())
         .type(file.getContentType())
         .filePath(filePath)
         .build());
     // Create and save a FileData entity in the repository with details about the uploaded file.

     file.transferTo(new File(filePath));
     // Transfer the uploaded file to the specified file path on the file system.

     if (fileData != null) {
         // If the file was successfully saved, update the imageFile field in the destination table.

         String destName = file.getOriginalFilename();
         Destinations destination = destinationsRepository.findDestinationByName(destName.substring(0, destName.length() - 4));
         // Find the destination by name based on the uploaded image file name (excluding the file extension).

         destination.setImageFile("assets/images/" + destName);
         // Update the imageFile field in the destination entity to reference the uploaded image.

         destinationsRepository.save(destination);
         // Save the updated destination entity in the repository.

         return "File uploaded successfully: " + filePath;
     }

     return null;
 }
 @Override
 public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
     // Method to download an image from the file system.

     Optional<FileData> fileData = fileDataRepository.findByName(fileName);
     // Find the FileData entity by the image file name.

     String filePath = fileData.get().getFilePath();
     // Get the file path of the image.

     byte[] images = Files.readAllBytes(new File(filePath).toPath());
     // Read the image as bytes from the file system.

     return images;
 }
 @Override
 public void imageDelete(int id) {
     // Method to delete an image from the file system.

     FileData fileData = fileDataRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFound("Image", "id", id));
     // Find the FileData entity by its ID or throw a ResourceNotFound exception if not found.

     fileDataRepository.delete(fileData);
     // Delete the FileData entity from the repository.
 }
}
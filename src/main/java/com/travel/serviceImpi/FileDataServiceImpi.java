package com.travel.serviceImpi;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

public class FileDataServiceImpi {

 @Autowired
 private FileDataRepository fileDataRepository;
 // Injects a repository for managing file data.

 @Autowired
 private DestinationsRepository destinationsRepository;
 // Injects a repository for managing destination data.

 // private ImageDataComDecom imageDataComDecom;
 // It seems to be a commented-out field for another functionality or dependency.
 
private final String DestFOLDER_PATH = "E:/MajorProjectFrontend/majorProjectFrontend/src/assets/images/";
 
 public String uploadImageToFileSystem(MultipartFile file, int id) throws IOException {
    

     String filePath = DestFOLDER_PATH + file.getOriginalFilename();
     
     FileData fileData = fileDataRepository.save(FileData.builder()
         .name(file.getOriginalFilename())
         .type(file.getContentType())
         .filePath(filePath)
         .build());
    
     file.transferTo(new File(filePath));
     

     if (fileData != null) {
        
         String destName = file.getOriginalFilename();
         Destinations destination = destinationsRepository.findById(id).orElseThrow();
         

         destination.setImageFile("assets/images/" + destName);
        

         destinationsRepository.save(destination);
         

         return "File uploaded successfully: " + filePath;
     }

     return null;
 }

 public void imageDelete(int id) {
     // Method to delete an image from the file system.

     FileData fileData = fileDataRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFound("Image", "id", id));
     // Find the FileData entity by its ID or throw a ResourceNotFound exception if not found.

     fileDataRepository.delete(fileData);
     // Delete the FileData entity from the repository.
 }
}
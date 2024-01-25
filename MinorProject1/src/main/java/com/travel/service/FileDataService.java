package com.travel.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileDataService {
	
	public String uploadImageToFileSystem(MultipartFile file) throws IOException;//method to upload image to the file system
	
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException ;//method to download image
	
	public void imageDelete(int id);//method to delete image
}
package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.entity.Review;

public interface ReviewReposiory extends JpaRepository<Review, Integer> {
	
	@Query("SELECT r FROM Review r WHERE r.location = :location AND r.hotelName = :hotelName")
    List<Review> findReviewsByLocationAndHotelName(String location, String hotelName);
	
	@Query("SELECT r FROM Review r WHERE r.location = :location AND r.packageName = :packageName")
    List<Review> findReviewsByLocationAndPackageName(String location, String packageName);
	
	@Query("SELECT r FROM Review r WHERE r.location = :location")
    List<Review> findReviewsByLocation(String location);
	
	@Query("SELECT r FROM Review r WHERE r.hotelName = :hotelName")
    List<Review> findReviewsByHotelName(String hotelName);
	
	@Query("SELECT r FROM Review r WHERE r.packageName = :packageName")
    List<Review> findReviewsByPackageName(String packageName);

}
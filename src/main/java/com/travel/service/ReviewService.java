package com.travel.service;

import java.util.List;

import com.travel.dto.ReviewDto;

public interface ReviewService {
	
	ReviewDto saveReview(ReviewDto reviewDto);
	
	List<ReviewDto> getAllReviews();
	
	List<ReviewDto> getReviewByLocationAndHotelName(String location, String hotelName);
	
	List<ReviewDto> getReviewByLocationAndPackageName(String location, String packageName);
}

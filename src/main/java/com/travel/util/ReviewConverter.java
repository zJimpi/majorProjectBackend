package com.travel.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.ActivityDto;
import com.travel.dto.ReviewDto;
import com.travel.entity.Activity;
import com.travel.entity.Review;

@Component
public class ReviewConverter {
	
	public Review convertDtoToEntity(ReviewDto reviewDto) {
		
		Review review = new Review();

		if (reviewDto != null) {
			BeanUtils.copyProperties(reviewDto, review);
		}

		return review;
	}

	// Converts Room Entity to RoomDto
	public ReviewDto convertEntityToDto(Review review) {
		
		ReviewDto reviewDto = new ReviewDto();

		if (review != null) {
			BeanUtils.copyProperties(review, reviewDto);
		}

		return reviewDto;
	}
}
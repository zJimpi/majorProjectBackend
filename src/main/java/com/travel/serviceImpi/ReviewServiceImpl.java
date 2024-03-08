package com.travel.serviceImpi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dto.ActivityDto;
import com.travel.dto.ReviewDto;
import com.travel.entity.Activity;
import com.travel.entity.Review;
import com.travel.repository.ReviewReposiory;
import com.travel.service.ReviewService;
import com.travel.util.ReviewConverter;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewReposiory reviewReposiory;
	
	@Autowired
	ReviewConverter reviewConverter;

	@Override
	public ReviewDto saveReview(ReviewDto reviewDto) {
		// TODO Auto-generated method stub
		
		Review review = reviewConverter.convertDtoToEntity(reviewDto);
		reviewReposiory.save(review);
		return reviewConverter.convertEntityToDto(review);
	}

	@Override
	public List<ReviewDto> getAllReviews() {
		// TODO Auto-generated method stub
		
		List<Review> reviews = reviewReposiory.findAll();
		List<ReviewDto> reviewDtos = new ArrayList<>();

		for (Review review : reviews) {
			ReviewDto reviewDto = reviewConverter.convertEntityToDto(review);
			reviewDtos.add(reviewDto);
		}

		return reviewDtos;
	}

	@Override
	public List<ReviewDto> getReviewByLocationAndHotelName(String location, String hotelName) {
		// TODO Auto-generated method stub
		List<Review> reviews = reviewReposiory.findReviewsByLocationAndHotelName(location,hotelName);
		List<ReviewDto> reviewDtos = new ArrayList<>();

		for (Review review : reviews) {
			ReviewDto reviewDto = reviewConverter.convertEntityToDto(review);
			reviewDtos.add(reviewDto);
		}

		return reviewDtos;
	}

	@Override
	public List<ReviewDto> getReviewByLocationAndPackageName(String location, String packageName) {
		// TODO Auto-generated method stub
		List<Review> reviews = reviewReposiory.findReviewsByLocationAndPackageName(location,packageName);
		List<ReviewDto> reviewDtos = new ArrayList<>();

		for (Review review : reviews) {
			ReviewDto reviewDto = reviewConverter.convertEntityToDto(review);
			reviewDtos.add(reviewDto);
		}

		return reviewDtos;
	}
}
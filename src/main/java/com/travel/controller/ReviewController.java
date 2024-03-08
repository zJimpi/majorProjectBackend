package com.travel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.dto.ActivityDto;
import com.travel.dto.ReviewDto;
import com.travel.service.ReviewService;
import com.travel.util.ReviewConverter;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ReviewConverter reviewConverter;
	
	@PostMapping("/saveReview")
	 public ReviewDto saveReview(@Valid @RequestBody ReviewDto reviewDto) {
		 return reviewService.saveReview(reviewDto);
	 }
	
	@GetMapping("/getReviewList")
    public List<ReviewDto> getReviewList() {
        return reviewService.getAllReviews();
    }

  
    @GetMapping("/getReviewByLocationAndHotelName/{location}/{hotelName}")
    public List<ReviewDto> getReviewByLocationAndHotelName(@PathVariable("location") String location, @PathVariable("hotelName") String hotelName) {
        return reviewService.getReviewByLocationAndHotelName(location,hotelName);
    }
    
    @GetMapping("/getReviewByLocationAndPackageName/{location}/{packageName}")
    public List<ReviewDto> getReviewByLocationAndPackageName(@PathVariable("location") String location, @PathVariable("packageName") String packageName) {
        return reviewService.getReviewByLocationAndPackageName(location,packageName);
    }
    
    @GetMapping("/getReviewByLocation/{location}")
    public List<ReviewDto> getReviewByLocation(@PathVariable("location") String location) {
        return reviewService.getReviewByLocation(location);
    }
}
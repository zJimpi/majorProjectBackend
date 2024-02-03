package com.travel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.dto.ActivityDto;
import com.travel.entity.Activity;
import com.travel.service.ActivityService;
import com.travel.util.ActivityConverter;

@RestController
@RequestMapping("/activity")
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityController {
	
	@Autowired
	ActivityService activityService;
	
	@Autowired
	ActivityConverter activityConverter;
	
	 @PostMapping("/saveActivity")
	 public ActivityDto saveActivity(@Valid @RequestBody ActivityDto activityDto) {
		 return activityService.saveActivity(activityDto);
	 }

	 
	 @DeleteMapping("/deleteActivityById/{id}")
	 public ResponseEntity<String> deleteActivityById(@PathVariable("id") Long activityId) {
	    	activityService.deleteActivityById(activityId);
	        return new ResponseEntity<>(activityId + " is deleted successfully!!", HttpStatus.OK);
	    }


	    @PutMapping("/updateActivityById/{id}")
	    public ActivityDto updateActivity(@PathVariable("id") Long activityId, @RequestBody ActivityDto activityDto) {
	    	final Activity activity = activityConverter.convertDtoToEntity(activityDto);
			return activityService.updateActivityById(activityId, activity);
	    }

	 
	    @GetMapping("/getActivityList")
	    public List<ActivityDto> getActivityList() {
	        return activityService.getActivityList();
	    }

	  
	    @GetMapping("/getActivityById/{id}")
	    public ActivityDto getActivityById(@PathVariable("id") Long activityId) {
	        return activityService.getActivityById(activityId);
	    }


	    @GetMapping("/getActivityListByPackageId/{packageId}")
	    public List<ActivityDto> getActivityByPackageId(@PathVariable("packageId") Long packageId) {
	    
	    	
	    	return activityService.getActivityListByPackageId(packageId);
	    }
	    
	    @PostMapping("/assignActivityId/{activityId}/toPackageId/{packageId}")
		public String assignActivityToPackage(@PathVariable("activityId") Long activityId, @PathVariable("packageId") Long packageId) {
			
	    	activityService.assignActivityIdToPackageId(activityId, packageId);
			 
			 return "room id "+activityId+" assigned to hotel id "+packageId;
				
		}

}
package com.travel.service;

import java.util.List;

import com.travel.dto.ActivityDto;
import com.travel.entity.Activity;

public interface ActivityService {
	
	ActivityDto saveActivity(ActivityDto activityDto);
	
	void deleteActivityById(Long activityId);
	
	ActivityDto updateActivityById(Long activityId, Activity activity);
	
	ActivityDto getActivityById(Long activityId);

	List<ActivityDto> getActivityList();

    List<ActivityDto> getActivityListByPackageId(Long activityId);
    
    public void assignActivityIdToPackageId(Long activityId, Long packageId);
	
}
package com.travel.serviceImpi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dto.ActivityDto;
import com.travel.entity.Activity;
import com.travel.entity.Package;
import com.travel.exception.ResourceNotFound;
import com.travel.repository.ActivityRepository;
import com.travel.repository.PackageRepository;
import com.travel.service.ActivityService;
import com.travel.util.ActivityConverter;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	ActivityConverter activityConverter;
	
	@Autowired
	PackageRepository packageRepository;

	@Override
	public ActivityDto saveActivity(ActivityDto activityDto) {
		
		Activity activity = activityConverter.convertDtoToEntity(activityDto);
		activityRepository.save(activity);
		return activityConverter.convertEntityToDto(activity);
	}

	@Override
	public void deleteActivityById(Long activityId) {
		Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new ResourceNotFound("Activity", "id", activityId));
		activityRepository.delete(activity);
	}

	@Override
	public ActivityDto updateActivityById(Long activityId, Activity activity) {
		Activity existingActivity = activityRepository.findById(activityId)
				.orElseThrow(() -> new ResourceNotFound("Activity", "id", activityId));

		existingActivity.setActivityName(activity.getActivityName());
		existingActivity.setActivityTiming(activity.getActivityTiming());
		existingActivity.setActivityDescription(activity.getActivityDescription());

		
		activityRepository.save(existingActivity);

		return activityConverter.convertEntityToDto(existingActivity);
	}
		

	@Override
	public ActivityDto getActivityById(Long activityId) {
		
		Activity activity = activityRepository.findById(activityId).orElseThrow(
				()-> new ResourceNotFound("Activity", "id", activityId));

		return activityConverter.convertEntityToDto(activity);
	}

	
	@Override
	public List<ActivityDto> getActivityList() {
		
		List<Activity> activities = activityRepository.findAll();
		List<ActivityDto> activityDtos = new ArrayList<>();

		for (Activity activity : activities) {
			ActivityDto activityDto = activityConverter.convertEntityToDto(activity);
			activityDtos.add(activityDto);
		}

		return activityDtos;
	}

	
	@Override
	public List<ActivityDto> getActivityListByPackageId(Long packageId) {
		
		List<Activity> activities =activityRepository.getActivityByPackageId(packageId);
		
		List<ActivityDto> activityDtos =new ArrayList<>();
		
		for(Activity activity: activities) {
			activityDtos.add(activityConverter.convertEntityToDto(activity));
		}
		return activityDtos;
	}

	@Override
	public void assignActivityIdToPackageId(Long activityId, Long packageId) {
		
		Activity activity = activityRepository.findById(activityId).orElseThrow(()->
		new ResourceNotFound("Activity", "id", activityId));
			
		Package pckg = packageRepository.findById(packageId).orElseThrow(()->
		new ResourceNotFound("Package", "id", packageId));
		
		
		activity.setPckg(pckg);
		

		activityRepository.save(activity);
		packageRepository.save(pckg);
		
	}

}

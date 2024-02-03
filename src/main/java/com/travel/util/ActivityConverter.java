package com.travel.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.ActivityDto;
import com.travel.entity.Activity;

@Component
public class ActivityConverter {
	
	public Activity convertDtoToEntity(ActivityDto activityDto) {
		
		Activity activityEntity = new Activity();

		if (activityDto != null) {
			BeanUtils.copyProperties(activityDto, activityEntity);
		}

		return activityEntity;
	}

	// Converts Room Entity to RoomDto
	public ActivityDto convertEntityToDto(Activity activityEntity) {
		
		ActivityDto activityDto = new ActivityDto();

		if (activityEntity != null) {
			BeanUtils.copyProperties(activityEntity, activityDto);
		}

		return activityDto;
	}

}
package com.travel.dto;


import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import com.travel.entity.Package;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDto {
	
	
	private Long activityId;
	
	@NotBlank(message = "Activity Name is required")
	private String activityName;
	
	@NotBlank(message = "Activity Timing is required")
	private String activityTiming;
	
	@NotBlank(message = "Activity Description is required")
	private String activityDescription;
	
	@ManyToOne
	private Package pckg;

}

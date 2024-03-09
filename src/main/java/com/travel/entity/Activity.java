package com.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long activityId;
	
	@Column(length= 70, nullable= false)
	private String activityName;
	
	@Column(length= 40, nullable= false)
	private String activityTiming;
	
	@Column(length= 200, nullable= false)
	private String activityDescription;
	
	@ManyToOne
	private Package pckg;
	
	private String imageFile;

}
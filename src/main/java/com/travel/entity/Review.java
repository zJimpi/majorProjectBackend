package com.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = true)
	private String location;
	
	@Column(nullable = true)
	private String hotelName;
	
	@Column(nullable = true)
	private String packageName;
	
	@Column(nullable = false)
	private String comment;
}
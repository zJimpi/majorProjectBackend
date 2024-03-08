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
public class CarBookings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carBookingId;
	
	@Column(nullable= false)
	private String username;
	
	@Column(nullable= false)
	private String pickUpDate;
	
	@Column(nullable= false)
	private String dropOffDate;
	
	@Column(nullable= false)
	private String location;
	
	@Column(nullable= false)
	private String acOrNonAc;
	
	@Column(nullable= false)
	private String carType;

}
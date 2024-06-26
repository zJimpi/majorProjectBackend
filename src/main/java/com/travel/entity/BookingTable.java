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
public class BookingTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = true)
	private Long packageId;
	
	@Column(nullable = true)
	private String packageName;
	
	@Column(nullable = false)
	private Long hotelId;
	
	@Column(nullable = false)
	private String hotelName;
	
	@Column(nullable = false)
	private Long[] roomIds;
	
	@Column(nullable = false)
	private int[] noOfRooms;
	
	@Column(nullable = false)
	private String[] roomTypes;
	
	@Column(nullable = true)
	private int adults;
	
	@Column(nullable = true)
	private int child;
	
	@Column(nullable = false)
	private String checkInDate;
	
	@Column(nullable = true)
	private String checkOutDate;
	
	@Column(nullable = true)
	private Long totalPrice;

}
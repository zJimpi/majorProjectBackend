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
	private Long userId;
	
	@Column(nullable = true)
	private Long packageId;
	
	@Column(nullable = true)
	private Long hotelId;
	
	@Column(nullable = true)
	private String[] roomTypes;
	
	@Column(nullable = true)
	private int[] NoOfRooms;
	
	@Column(nullable = true)
	private int NoOfAdults;
	
	@Column(nullable = true)
	private int NoOfChild;
	
	@Column(nullable = false)
	private String dateRange;
	
	@Column(nullable = true)
	private Long totalPrice;

}
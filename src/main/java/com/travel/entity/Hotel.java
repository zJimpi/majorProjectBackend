package com.travel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotelId;

	@Column(length = 50, nullable = false)
	private String hotelName;

	@Column(length = 100, nullable = false)
	private String hotelLocation;
	
	@Column(nullable=false)
	private String state;
	
	@Column(length = 255, nullable = false)
	private String address;

	@Column(length = 20)
	private String hotelMobileNumber;

	@Column(length = 50)
	private String managerName;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<Room> room;

}

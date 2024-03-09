package com.travel.entity;

import java.util.List;

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
	
	@Column(length= 50, nullable= false)
	private String hotelName;
	
	@Column(length= 50, nullable= false)
	private String location;
	
	@Column(nullable= false)
	private Long startingPrice;
	
	@Column(nullable = false)
	private String address;
	
	@Column(length= 11, nullable= false)
	private String number;
	
	@Column(nullable= true)
	private String imgUrl;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Room> room;
	
	private String imageFile;

}
	 

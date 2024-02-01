package com.travel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private Long hotelid;
	
	@Column(length= 50, nullable= false)
	private String hotelName;
	
	@Column(length= 50, nullable= false)
	private String location;
	
	@Column(length= 50, nullable= false)
	private String state;
	
	@Column(nullable = false)
	private String address;
	
	@Column(length= 11, nullable= false)
	private String number;
	
	@Column(length= 50, nullable= false)
	private String manager;
	
	@Column(nullable= true)
	private String imgUrl;
	
	@OneToMany
	private List<Room> room;

}

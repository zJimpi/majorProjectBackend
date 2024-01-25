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
@Table(name = "hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "htl_id")
    private int hotelId;

    @Column(name = "htl_name", length = 50, nullable = false)
    private String hotelName;

    @Column(name = "htl_location", length = 100, nullable = false)
    private String hotelLocation;

    @Column(name = "Address", length = 255, nullable = false)
    private String address;

    @Column(name = "Hotel_Mobile_Number", length = 20)
    private String hotelMobileNumber;

    @Column(name = "Manager_Name", length = 50)
    private String managerName;


}

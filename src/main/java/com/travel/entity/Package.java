package com.travel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Packages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pckg_ID")
    private Long pckgId;

    @Column(name = "Pckg_Name", nullable = false)
    private String pckgName;

    @Column(name = "Package_code", nullable = false, unique = true)
    private String packageCode;
    
    @Column(nullable = false)
    private String packageDuration;

    @Column(name = "Location", nullable = false)
    private String location;

    @Column(name = "Price", nullable = false)
    private Double price;
    
    @Column(name = "Spots", nullable = false)
    private String spots;
    
    @OneToMany
	private List<Activity> activity;
    
    private String imageFile;
   
}
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

    @Column(name = "Location", nullable = false)
    private String location;

    @Column(name = "Price", nullable = false)
    private Double price;
}

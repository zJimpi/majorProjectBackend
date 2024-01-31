//package com.travel.entity;
//
//import javax.persistence.*;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "Spots")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Spot {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Spot_Id")
//    private Long spotId;
//
//    @Column(name = "Spot_Name", nullable = false)
//    private String spotName;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "package_id")
//    private Package travelPackage;
//
//    // Constructors, getters, setters
//}

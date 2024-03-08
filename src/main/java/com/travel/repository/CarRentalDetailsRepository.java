package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.CarRentalDetails;

public interface CarRentalDetailsRepository extends JpaRepository<CarRentalDetails, Integer> {

}

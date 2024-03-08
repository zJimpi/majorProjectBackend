package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.entity.CarBookings;

public interface CarBookingsRepository extends JpaRepository<CarBookings, Integer>{
	
	@Query("SELECT b FROM CarBookings b WHERE b.username = :username")
	 List<CarBookings> findCarBookingsByUsername(@Param("username") String username);

}

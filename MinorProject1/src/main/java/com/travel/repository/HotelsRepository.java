package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.entity.Hotel;

public interface HotelsRepository extends JpaRepository<Hotel, Integer> {

    @Query("SELECT h FROM Hotel h WHERE h.hotelName = :name")
    Hotel findHotelByName(@Param("name") String name);


	Optional<Hotel> findByHotelNameAndHotelLocationAndHotelMobileNumber(String hotelName, String hotelLocation,
			String hotelMobileNumber);

	Optional<Hotel> findByHotelNameAndHotelLocationAndHotelMobileNumberAndHotelIdNot(String hotelName,
			String hotelLocation, String hotelMobileNumber, int excludeHotelId);

    // Other CRUD methods for the Hotel entity are inherited from JpaRepository.
}

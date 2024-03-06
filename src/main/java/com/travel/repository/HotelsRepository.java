package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.entity.Hotel;


public interface HotelsRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h WHERE h.hotelName = :name")
    Hotel findHotelByName(@Param("name") String name);
    
    @Query("SELECT DISTINCT h FROM Hotel h LEFT JOIN FETCH h.room r")
    List<Hotel> findAllHotelsWithRooms();

}
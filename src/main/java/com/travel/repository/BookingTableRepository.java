package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.entity.BookingTable;

public interface BookingTableRepository extends JpaRepository<BookingTable, Long> {
	
	 @Query("SELECT b FROM BookingTable b WHERE b.userName = :userName")
	 List<BookingTable> findBookingsByUsername(@Param("userName") String userName);
}

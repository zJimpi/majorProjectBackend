package com.travel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.entity.Hotel;
import com.travel.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotel(Hotel hotel);

    Optional<Room> findByHotelAndRoomTypeAndRoomName(Hotel hotel, String roomType, String roomName);
    
    @Query("from Room where hotel=(from Hotel where hotelId=:h)")
    List<Room> getRoomByHotelId(@Param("h") Long HotelId);

}

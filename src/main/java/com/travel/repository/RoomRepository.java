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

    
    @Query("SELECT r FROM Room r WHERE r.id = :roomId AND r.hotel = :hotel")
    Optional<Room> findByIdAndHotel(@Param("roomId") Long roomId, @Param("hotel") Hotel hotel);
}

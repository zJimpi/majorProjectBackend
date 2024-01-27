package com.travel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.Hotel;
import com.travel.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotel(Hotel hotel);

    Optional<Room> findByHotelAndRoomTypeAndRoomName(Hotel hotel, String roomType, String roomName);

}

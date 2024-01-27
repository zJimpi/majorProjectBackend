package com.travel.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.RoomDto;
import com.travel.entity.Room;

@Component
public class RoomConverter {

    // Converts RoomDto to Room Entity
    public Room convertDtoToEntity(RoomDto roomDto) {
        Room roomEntity = new Room();

        if (roomDto != null) {
            BeanUtils.copyProperties(roomDto, roomEntity);
            // You may need additional logic here for handling the hotel, if applicable
        }

        return roomEntity;
    }

    // Converts Room Entity to RoomDto
    public RoomDto convertEntityToDto(Room roomEntity) {
        RoomDto roomDto = new RoomDto();

        if (roomEntity != null) {
            BeanUtils.copyProperties(roomEntity, roomDto);
            // You may need additional logic here for handling the hotel, if applicable
        }

        return roomDto;
    }
}

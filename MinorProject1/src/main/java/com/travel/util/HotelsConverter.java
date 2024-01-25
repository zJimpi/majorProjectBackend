package com.travel.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.HotelDto;
import com.travel.entity.Hotel;

@Component
public class HotelsConverter {

    // Converts HotelDto to Hotel Entity
    public Hotel convertDtoToEntity(HotelDto hotelDto) {
        Hotel hotel = new Hotel();

        if (hotelDto != null) {
            BeanUtils.copyProperties(hotelDto, hotel);
        }

        return hotel;
    }

    // Converts Hotel Entity to HotelDto
    public HotelDto convertEntityToDto(Hotel hotel) {
        HotelDto hotelDto = new HotelDto();

        if (hotel != null) {
            BeanUtils.copyProperties(hotel, hotelDto);
        }

        return hotelDto;
    }
}

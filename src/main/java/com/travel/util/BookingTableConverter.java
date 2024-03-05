package com.travel.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.ActivityDto;
import com.travel.dto.bookingTableDto;
import com.travel.entity.Activity;
import com.travel.entity.BookingTable;

@Component
public class BookingTableConverter {
	
	public BookingTable convertDtoToEntity(bookingTableDto BookingTableDto) {
		
		BookingTable bookingTableEntity = new BookingTable();

		if (BookingTableDto != null) {
			BeanUtils.copyProperties(BookingTableDto, bookingTableEntity);
		}

		return bookingTableEntity;
	}

	// Converts Room Entity to RoomDto
	public bookingTableDto convertEntityToDto(BookingTable bookingTableEntity) {
		
		bookingTableDto BookingTableDto = new bookingTableDto();

		if (bookingTableEntity != null) {
			BeanUtils.copyProperties(bookingTableEntity, BookingTableDto);
		}

		return BookingTableDto;
	}


}

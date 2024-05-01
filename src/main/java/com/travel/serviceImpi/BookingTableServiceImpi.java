package com.travel.serviceImpi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dto.bookingTableDto;
import com.travel.entity.BookingTable;
import com.travel.entity.Hotel;
import com.travel.entity.Package;
import com.travel.entity.Room;
import com.travel.exception.ResourceNotFound;
import com.travel.repository.BookingTableRepository;
import com.travel.repository.HotelsRepository;
import com.travel.repository.PackageRepository;
import com.travel.repository.RoomRepository;
import com.travel.service.BookingTableService;
import com.travel.util.BookingTableConverter;

@Service
public class BookingTableServiceImpi implements BookingTableService {
	
	@Autowired
	private BookingTableConverter bookingTableConverter;
	
	@Autowired
	private BookingTableRepository bookingTableRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private HotelsRepository hotelsRepository;
	
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public bookingTableDto saveBookingTable(bookingTableDto BookingTableDto) {
		// TODO Auto-generated method stub
		
		BookingTable bookingTable = bookingTableConverter.convertDtoToEntity(BookingTableDto);
		if(bookingTable.getPackageId()!=null)
		{
			Package packageEntity = packageRepository.findById(bookingTable.getPackageId()).orElseThrow(() -> new ResourceNotFound("Package", "id", bookingTable.getPackageId()));
			packageEntity.setNoOfBookings(packageEntity.getNoOfBookings()+1);
			bookingTable.setPackageName(packageEntity.getPckgName());
		}
		Hotel hotel = hotelsRepository.findById(bookingTable.getHotelId()).orElseThrow(() -> new ResourceNotFound("Hotel", "id", bookingTable.getHotelId()));
		bookingTable.setHotelName(hotel.getHotelName());
		String[] roomTypes = new String[bookingTable.getRoomIds().length];
		for (int i = 0; i < bookingTable.getRoomIds().length; i++) {
			Room room = roomRepository.findById(bookingTable.getRoomIds()[i]).orElseThrow(() -> new ResourceNotFound("Room", "id", bookingTable.getHotelId()));
			roomTypes[i] = room.getRoomType();
		}
		bookingTable.setRoomTypes(roomTypes);
		bookingTableRepository.save(bookingTable);
		return bookingTableConverter.convertEntityToDto(bookingTable);
		
	}

	@Override
	public void deleteBookingTableById(Long bookingId) {
		// TODO Auto-generated method stub
		
		BookingTable bookingTable = bookingTableRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFound("Booking Table", "id", bookingId));
		bookingTableRepository.delete(bookingTable);
		
	}

	@Override
	public bookingTableDto getBookingTableById(Long bookingId) {
		// TODO Auto-generated method stub
		
		BookingTable bookingTable = bookingTableRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFound("Booking Table", "id", bookingId));
		return bookingTableConverter.convertEntityToDto(bookingTable);
	}

	@Override
	public List<bookingTableDto> getBookingTableByUsername(String Username) {
		// TODO Auto-generated method stub
		
		List<BookingTable> bookingTables = bookingTableRepository.findBookingsByUsername(Username);
		List<bookingTableDto> bookingTableDtos = new ArrayList<>();

		for (BookingTable bookingTable : bookingTables) {
			bookingTableDto BookingTableDto = bookingTableConverter.convertEntityToDto(bookingTable);
			bookingTableDtos.add(BookingTableDto);
		}
		
		return bookingTableDtos;
	}

	@Override
	public void updatePriceByBookingId(Long bookingId, Long price) {
		// TODO Auto-generated method stub
		
		BookingTable bookingTable = bookingTableRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFound("Booking Table", "id", bookingId));
		bookingTable.setTotalPrice(price);
		
		bookingTableRepository.save(bookingTable);
		
	}
}
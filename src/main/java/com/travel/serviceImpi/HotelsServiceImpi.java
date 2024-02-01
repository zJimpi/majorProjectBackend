package com.travel.serviceImpi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.travel.dto.HotelDto;
import com.travel.dto.RoomDto;
import com.travel.entity.Hotel;
import com.travel.entity.Room;
import com.travel.exception.ResourceNotFound;
import com.travel.repository.HotelsRepository;
import com.travel.repository.RoomRepository;
import com.travel.service.HotelsService;
import com.travel.util.HotelsConverter;
import com.travel.util.RoomConverter;

@Service
public class HotelsServiceImpi implements HotelsService {

	@Autowired
	private HotelsRepository hotelsRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private HotelsConverter hotelsConverter;

	@Autowired
	private RoomConverter roomConverter;

	@Override
	public HotelDto saveHotel(Hotel hotel) {
		//validateHotelUniqueness(hotel.getHotelName(), hotel.getHotelLocation(), hotel.getHotelMobileNumber(), 0);

		hotelsRepository.save(hotel);

		return hotelsConverter.convertEntityToDto(hotel);
	}

//	private void validateHotelUniqueness(String hotelName, String hotelLocation, String hotelMobileNumber,
//			ong excludeHotelId) {
//		Optional<Hotel> existingHotel = hotelsRepository
//				.findByHotelNameAndHotelLocationAndHotelMobileNumberAndHotelIdNot(hotelName, hotelLocation,
//						hotelMobileNumber, excludeHotelId);
//		if (existingHotel.isPresent()) {
//			throw new DataIntegrityViolationException(
//					"A hotel with the same name, location, and mobile number already exists.");
//		}
//	}
	
	@Override
	public List<HotelDto> getHotelList() {
		List<Hotel> hotels = hotelsRepository.findAll();
		List<HotelDto> hotelDtos = new ArrayList<>();

		for (Hotel h : hotels) {
			HotelDto hotelDto = hotelsConverter.convertEntityToDto(h);
			hotelDtos.add(hotelDto);
		}

		return hotelDtos;
	}


	@Override
	public void deleteHotelById(Long hotelId) {
		Hotel hotel = hotelsRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));
		hotelsRepository.delete(hotel);
	}

	@Override
	public HotelDto getHotelById(Long hotelId) {
		// Call the repository or any necessary logic to get the hotel by its ID
		Hotel hotel = hotelsRepository.findById(hotelId).orElseThrow(
				()-> new ResourceNotFound("Hotel", "id", hotelId));

		return hotelsConverter.convertEntityToDto(hotel);
	
	}


	@Override
	public HotelDto updateHotel(Long hotelId, Hotel hotel) {
		
		Hotel existingHotel = hotelsRepository.findById(hotelId).orElseThrow(()->
		new ResourceNotFound("Hotel", "id", hotelId));
		
		existingHotel.setAddress(hotel.getAddress());
		existingHotel.setHotelLocation(hotel.getHotelLocation());
		existingHotel.setHotelMobileNumber(hotel.getHotelMobileNumber());
		existingHotel.setHotelName(hotel.getHotelName());
		existingHotel.setManagerName(hotel.getManagerName());
		existingHotel.setState(hotel.getState());
		
		hotelsRepository.save(existingHotel);
		
		
		return hotelsConverter.convertEntityToDto(existingHotel);
	}

	
}

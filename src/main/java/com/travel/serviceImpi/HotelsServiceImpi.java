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
		validateHotelUniqueness(hotel.getHotelName(), hotel.getHotelLocation(), hotel.getHotelMobileNumber(), 0);

		hotelsRepository.save(hotel);

		return hotelsConverter.convertEntityToDto(hotel);
	}

    @Override
    public HotelDto updateHotel(int hotelId, HotelDto existingHotelDto, List<RoomDto> roomsDto) {
        Hotel existingHotel = getHotelByIdOrThrow(hotelId);

        existingHotel.setHotelName(existingHotelDto.getHotelName());
        existingHotel.setHotelLocation(existingHotelDto.getHotelLocation());
        existingHotel.setAddress(existingHotelDto.getAddress());
        existingHotel.setHotelMobileNumber(existingHotelDto.getHotelMobileNumber());
        existingHotel.setManagerName(existingHotelDto.getManagerName());

        validateHotelUniqueness(existingHotel.getHotelName(), existingHotel.getHotelLocation(),
                existingHotel.getHotelMobileNumber(), hotelId);

        hotelsRepository.save(existingHotel);

        updateRoomsForHotel(existingHotel, roomsDto);

        return hotelsConverter.convertEntityToDto(existingHotel);
    }

	@Override
	public void deleteHotelById(int hotelId) {
		Hotel hotel = getHotelByIdOrThrow(hotelId);
		hotelsRepository.delete(hotel);
	}

	@Override
	public List<HotelDto> getHotelList() {
		List<Hotel> hotels = hotelsRepository.findAll();
		return hotelsConverter.convertEntityListToDtoList(hotels);
	}

	@Override
	public HotelDto getHotelById(int hotelId) {
		Hotel hotel = getHotelByIdOrThrow(hotelId);
		return hotelsConverter.convertEntityToDto(hotel);
	}

	@Override
	public RoomDto updateRoomForHotel(int hotelId, Long roomId, RoomDto roomDto) {
		Hotel hotel = getHotelByIdOrThrow(hotelId);
		Room existingRoom = getRoomByIdAndHotelOrThrow(roomId, hotel);

		existingRoom.setRoomType(roomDto.getRoomType());
		existingRoom.setRoomName(roomDto.getRoomName());
		existingRoom.setPricePerDay(roomDto.getPricePerDay());

		validateRoomUniquenessForHotel(hotelId, existingRoom.getRoomType(), existingRoom.getRoomName());

		roomRepository.save(existingRoom);

		return roomConverter.convertEntityToDto(existingRoom);
	}

	@Override
	public void deleteRoomForHotel(int hotelId, Long roomId) {
		Hotel hotel = getHotelByIdOrThrow(hotelId);
		Room room = getRoomByIdAndHotelOrThrow(roomId, hotel);
		roomRepository.delete(room);
	}

	@Override
	public List<RoomDto> getRoomsForHotel(int hotelId) {
		Hotel hotel = getHotelByIdOrThrow(hotelId);
		List<Room> rooms = roomRepository.findByHotel(hotel);
		return roomConverter.convertEntityListToDtoList(rooms);
	}

	@Override
	public RoomDto saveRoomForHotel(int hotelId, RoomDto roomDto) {
		Hotel hotel = getHotelByIdOrThrow(hotelId);

		validateRoomUniquenessForHotel(hotelId, roomDto.getRoomType(), roomDto.getRoomName());

		Room roomEntity = roomConverter.convertDtoToEntity(roomDto);
		roomEntity.setHotel(hotel);

		roomRepository.save(roomEntity);

		return roomConverter.convertEntityToDto(roomEntity);
	}

	private void validateHotelUniqueness(String hotelName, String hotelLocation, String hotelMobileNumber,
			int excludeHotelId) {
		Optional<Hotel> existingHotel = hotelsRepository
				.findByHotelNameAndHotelLocationAndHotelMobileNumberAndHotelIdNot(hotelName, hotelLocation,
						hotelMobileNumber, excludeHotelId);
		if (existingHotel.isPresent()) {
			throw new DataIntegrityViolationException(
					"A hotel with the same name, location, and mobile number already exists.");
		}
	}

	private void validateRoomUniquenessForHotel(int hotelId, String roomType, String roomName) {
		Hotel hotel = getHotelByIdOrThrow(hotelId);
		Optional<Room> existingRoom = roomRepository.findByHotelAndRoomTypeAndRoomName(hotel, roomType, roomName);
		if (existingRoom.isPresent()) {
			throw new DataIntegrityViolationException("A room with the same details already exists for the hotel.");
		}
	}

	private Hotel getHotelByIdOrThrow(int hotelId) {
		return hotelsRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));
	}

	private Room getRoomByIdAndHotelOrThrow(Long roomId, Hotel hotel) {
		return roomRepository.findByIdAndHotel(roomId, hotel)
				.orElseThrow(() -> new ResourceNotFound("Room", "id", roomId));
	}

	private void updateRoomsForHotel(Hotel hotel, List<RoomDto> roomsDto) {
		List<Room> existingRooms = roomRepository.findByHotel(hotel);

		existingRooms.removeIf(existingRoom -> roomsDto.stream().noneMatch(
				roomDto -> roomDto.getRoomId() != null && roomDto.getRoomId().equals(existingRoom.getRoomId())));

		for (RoomDto roomDto : roomsDto) {
			if (roomDto.getRoomId() != null) {
				Room existingRoom = existingRooms.stream().filter(room -> roomDto.getRoomId().equals(room.getRoomId()))
						.findFirst().orElseThrow(() -> new ResourceNotFound("Room", "id", roomDto.getRoomId()));

				existingRoom.setRoomType(roomDto.getRoomType());
				existingRoom.setRoomName(roomDto.getRoomName());
				existingRoom.setPricePerDay(roomDto.getPricePerDay());

				roomRepository.save(existingRoom);
			} else {
				Room newRoom = roomConverter.convertDtoToEntity(roomDto);
				newRoom.setHotel(hotel);
				roomRepository.save(newRoom);
			}
		}
	}
}

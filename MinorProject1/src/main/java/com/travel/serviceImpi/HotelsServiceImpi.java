package com.travel.serviceImpi;

import java.util.ArrayList;
import java.util.List;

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
		// Check if a hotel with the same name, location, and mobile number already
		// exists
		if (hotelAlreadyExists(hotel.getHotelName(), hotel.getHotelLocation(), hotel.getHotelMobileNumber())) {
			throw new DataIntegrityViolationException(
					"A hotel with the same name, location, and mobile number already exists.");
		}

		// Save the hotel if the check passes
		hotelsRepository.save(hotel);

		// Convert and return the saved hotel as a DTO
		return hotelsConverter.convertEntityToDto(hotel);
	}

	@Override
	public HotelDto updateHotel(int hotelId, Hotel hotel) {
		Hotel existingHotel = hotelsRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));

		existingHotel.setHotelName(hotel.getHotelName());
		existingHotel.setHotelLocation(hotel.getHotelLocation());
		existingHotel.setAddress(hotel.getAddress());
		existingHotel.setHotelMobileNumber(hotel.getHotelMobileNumber());
		existingHotel.setManagerName(hotel.getManagerName());

		// Check if a hotel with the same name, location, and mobile number already
		// exists
		if (hotelAlreadyExists(existingHotel.getHotelName(), existingHotel.getHotelLocation(),
				existingHotel.getHotelMobileNumber(), hotelId)) {
			throw new DataIntegrityViolationException(
					"A hotel with the same name, location, and mobile number already exists.");
		}

		// Save the updated hotel if the check passes
		hotelsRepository.save(existingHotel);

		// Convert and return the updated hotel as a DTO
		return hotelsConverter.convertEntityToDto(existingHotel);
	}

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

	// Additional method to check if a hotel with the same name, location, and
	// mobile number already exists
	private boolean hotelAlreadyExists(String hotelName, String hotelLocation, String hotelMobileNumber) {
		return hotelsRepository
				.findByHotelNameAndHotelLocationAndHotelMobileNumber(hotelName, hotelLocation, hotelMobileNumber)
				.isPresent();
	}

	// Additional method to check if a hotel with the same name, location, and
	// mobile number already exists excluding a specific hotelId
	private boolean hotelAlreadyExists(String hotelName, String hotelLocation, String hotelMobileNumber,
			int excludeHotelId) {
		return hotelsRepository.findByHotelNameAndHotelLocationAndHotelMobileNumberAndHotelIdNot(hotelName,
				hotelLocation, hotelMobileNumber, excludeHotelId).isPresent();
	}

	@Override
	public void deleteHotelById(int hotelId) {
		Hotel hotel = hotelsRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));
		hotelsRepository.delete(hotel);
	}

	@Override
	public HotelDto getHotelById(int hotelId) {
		// Call the repository or any necessary logic to get the hotel by its ID
		Hotel hotel = hotelsRepository.findById(hotelId).orElse(null);

		// Convert the retrieved hotel entity to DTO
		if (hotel != null) {
			return hotelsConverter.convertEntityToDto(hotel);
		} else {
			// Return null or throw an exception based on your design
			return null;
		}
	}

	@Override
	public RoomDto saveRoomForHotel(int hotelId, RoomDto roomDto) {
		// Retrieve the hotel entity
		Hotel hotel = hotelsRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));

		// Convert the RoomDto to Room entity
		Room roomEntity = roomConverter.convertDtoToEntity(roomDto);
		roomEntity.setHotel(hotel);

		// Save the room entity
		roomRepository.save(roomEntity);

		// Convert and return the saved room as a DTO
		return roomConverter.convertEntityToDto(roomEntity);
	}

	@Override
	public List<RoomDto> getRoomsForHotel(int hotelId) {
		// Retrieve the hotel entity
		Hotel hotel = hotelsRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));

		// Retrieve the rooms associated with the hotel
		List<Room> rooms = roomRepository.findByHotel(hotel);
		List<RoomDto> roomDtos = new ArrayList<>();

		for (Room room : rooms) {
			RoomDto roomDto = roomConverter.convertEntityToDto(room);
			roomDtos.add(roomDto);
		}

		return roomDtos;
	}

	// Additional method to check if a room with the same details already exists for
	// a hotel
	private boolean roomAlreadyExistsForHotel(int hotelId, String roomType, String roomName) {
		Hotel hotel = hotelsRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));
		return roomRepository.findByHotelAndRoomTypeAndRoomName(hotel, roomType, roomName).isPresent();
	}

	@Override
	public void deleteRoomForHotel(int hotelId, Long roomId) {
		// Retrieve the hotel entity
		Hotel hotel = hotelsRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));

		// Retrieve the room entity
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFound("Room", "id", roomId));

		// Check if the room belongs to the specified hotel
		if (!room.getHotel().equals(hotel)) {
			throw new ResourceNotFound("Room", "id", roomId);
		}

		// Delete the room
		roomRepository.delete(room);
	}

	@Override
	public RoomDto updateRoomForHotel(int hotelId, Long roomId, RoomDto roomDto) {
		// Retrieve the hotel entity
		Hotel hotel = hotelsRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));

		// Retrieve the room entity
		Room existingRoom = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFound("Room", "id", roomId));

		// Check if the room belongs to the specified hotel
		if (!existingRoom.getHotel().equals(hotel)) {
			throw new ResourceNotFound("Room", "id", roomId);
		}

		// Update the properties of the existing room with the values from the DTO
		existingRoom.setRoomType(roomDto.getRoomType());
		existingRoom.setRoomName(roomDto.getRoomName());
		existingRoom.setPricePerDay(roomDto.getPricePerDay());
		// Update other properties as needed

		// Check if a room with the same details already exists for the hotel
		if (roomAlreadyExistsForHotel(hotelId, existingRoom.getRoomType(), existingRoom.getRoomName())) {
			throw new DataIntegrityViolationException("A room with the same details already exists for the hotel.");
		}

		// Save the updated room
		roomRepository.save(existingRoom);

		// Convert and return the updated room as a DTO
		return roomConverter.convertEntityToDto(existingRoom);
	}

	@Override
	public HotelDto updateHotel(int hotelId, HotelDto existingHotelDto, List<RoomDto> roomsDto) {
		// Retrieve the existing hotel entity
		Hotel existingHotel = hotelsRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));

		// Update the properties of the existing hotel with the values from the DTO
		existingHotel.setHotelName(existingHotelDto.getHotelName());
		existingHotel.setHotelLocation(existingHotelDto.getHotelLocation());
		existingHotel.setAddress(existingHotelDto.getAddress());
		existingHotel.setHotelMobileNumber(existingHotelDto.getHotelMobileNumber());
		existingHotel.setManagerName(existingHotelDto.getManagerName());
		// Update other properties as needed

		// Check if a hotel with the same name, location, and mobile number already
		// exists
		if (hotelAlreadyExists(existingHotel.getHotelName(), existingHotel.getHotelLocation(),
				existingHotel.getHotelMobileNumber(), hotelId)) {
			throw new DataIntegrityViolationException(
					"A hotel with the same name, location, and mobile number already exists.");
		}

		// Save the updated hotel
		hotelsRepository.save(existingHotel);

		// Update rooms associated with the hotel
		updateRoomsForHotel(existingHotel, roomsDto);

		// Convert and return the updated hotel as a DTO
		return hotelsConverter.convertEntityToDto(existingHotel);
	}

	// Helper method to update rooms associated with the hotel
	private void updateRoomsForHotel(Hotel hotel, List<RoomDto> roomsDto) {
		List<Room> existingRooms = roomRepository.findByHotel(hotel);

		// Remove rooms that are not present in the updated list
		existingRooms.removeIf(existingRoom -> roomsDto.stream().noneMatch(
				roomDto -> roomDto.getRoomId() != null && roomDto.getRoomId().equals(existingRoom.getRoomId())));

		// Update existing rooms
		for (RoomDto roomDto : roomsDto) {
			if (roomDto.getRoomId() != null) {
				Room existingRoom = existingRooms.stream().filter(room -> roomDto.getRoomId().equals(room.getRoomId()))
						.findFirst().orElseThrow(() -> new ResourceNotFound("Room", "id", roomDto.getRoomId()));

				existingRoom.setRoomType(roomDto.getRoomType());
				existingRoom.setRoomName(roomDto.getRoomName());
				existingRoom.setPricePerDay(roomDto.getPricePerDay());
				// Update other room properties as needed

				// Save the updated room
				roomRepository.save(existingRoom);
			} else {
				// Add new rooms
				Room newRoom = roomConverter.convertDtoToEntity(roomDto);
				newRoom.setHotel(hotel);
				roomRepository.save(newRoom);
			}
		}
	}

	@Override
	public HotelDto updateHotel(int hotelId, HotelDto existingHotelDto) {
	    // Retrieve the existing hotel entity
	    Hotel existingHotel = hotelsRepository.findById(hotelId)
	            .orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));

	    // Update the properties of the existing hotel with the values from the DTO
	    existingHotel.setHotelName(existingHotelDto.getHotelName());
	    existingHotel.setHotelLocation(existingHotelDto.getHotelLocation());
	    existingHotel.setAddress(existingHotelDto.getAddress());
	    existingHotel.setHotelMobileNumber(existingHotelDto.getHotelMobileNumber());
	    existingHotel.setManagerName(existingHotelDto.getManagerName());
	    // Update other properties as needed

	    // Check if a hotel with the same name, location, and mobile number already exists
	    if (hotelAlreadyExists(existingHotel.getHotelName(), existingHotel.getHotelLocation(),
	            existingHotel.getHotelMobileNumber(), hotelId)) {
	        throw new DataIntegrityViolationException(
	                "A hotel with the same name, location, and mobile number already exists.");
	    }

	    // Save the updated hotel
	    hotelsRepository.save(existingHotel);

	    // Convert and return the updated hotel as a DTO
	    return hotelsConverter.convertEntityToDto(existingHotel);
	}

}

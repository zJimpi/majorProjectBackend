package com.travel.serviceImpi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.travel.dto.RoomDto;
import com.travel.entity.Room;
import com.travel.exception.ResourceNotFound;
import com.travel.repository.HotelsRepository;
import com.travel.repository.RoomRepository;
import com.travel.service.HotelsService;
import com.travel.service.RoomService;
import com.travel.util.RoomConverter;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomConverter roomConverter;

	@Autowired
	private HotelsRepository hotelRepository;

	HotelsService h = new HotelsServiceImpi();

	@Override
	public RoomDto saveRoom(RoomDto roomDto) {
		// Convert the DTO to Entity
		Room roomEntity = roomConverter.convertDtoToEntity(roomDto);

		// Save the room entity
		roomRepository.save(roomEntity);

		// Convert and return the saved room as a DTO
		return roomConverter.convertEntityToDto(roomEntity);
	}

	@Override
	public void deleteRoomById(Long roomId) {
		Room roomEntity = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFound("Room", "id", roomId));
		roomRepository.delete(roomEntity);
	}

	@Override
	public RoomDto updateRoom(Long roomId, RoomDto roomDto) {
		Room existingRoom = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFound("Room", "id", roomId));

		existingRoom.setRoomType(roomDto.getRoomType());
		existingRoom.setRoomName(roomDto.getRoomName());
		existingRoom.setPricePerDay(roomDto.getPricePerDay());
		// Set additional fields as needed

		// Save the updated room
		roomRepository.save(existingRoom);

		// Convert and return the updated room as a DTO
		return roomConverter.convertEntityToDto(existingRoom);
	}

	@Override
	public List<RoomDto> getRoomList() {
		List<Room> rooms = roomRepository.findAll();
		List<RoomDto> roomDtos = new ArrayList<>();

		for (Room room : rooms) {
			RoomDto roomDto = roomConverter.convertEntityToDto(room);
			roomDtos.add(roomDto);
		}

		return roomDtos;
	}

	@Override
	public RoomDto getRoomById(Long roomId) {
		// Call the repository or any necessary logic to get the room by its ID
		Optional<Room> optionalRoom = roomRepository.findById(roomId);

		// Check if the room is found
		if (optionalRoom.isPresent()) {
			// Convert the retrieved room entity to DTO
			Room roomEntity = optionalRoom.get();
			return roomConverter.convertEntityToDto(roomEntity);
		} else {
			// Handle the case where the room with the given ID does not exist
			throw new ResourceNotFound("Room", "id", roomId);
		}
	}

	@Override
	@Transactional
	public List<RoomDto> updateRoomsForHotel(int hotelId, @Valid List<RoomDto> rooms) {
	    // Retrieve the hotel entity
	    com.travel.entity.Hotel hotel = hotelRepository.findById(hotelId)
	            .orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));

	    List<RoomDto> updatedRooms = new ArrayList<>();

	    try {
	        // Begin a transaction
	        for (RoomDto roomDto : rooms) {
	            if (isDuplicateRoom(hotelId, roomDto)) {
	                // Handle duplicate room logic, you can throw an exception or handle it as needed
	                throw new RuntimeException("Duplicate room found: " + roomDto.getRoomName());
	            }

	            if (roomDto.getRoomId() != null) {
	                // Existing room, update it
	                Room existingRoom = roomRepository.findById(roomDto.getRoomId())
	                        .orElseThrow(() -> new ResourceNotFound("Room", "id", roomDto.getRoomId()));

	                existingRoom.setRoomType(roomDto.getRoomType());
	                existingRoom.setRoomName(roomDto.getRoomName());
	                existingRoom.setPricePerDay(roomDto.getPricePerDay());
	                existingRoom.setHotel(hotel);
	                roomRepository.save(existingRoom);

	                updatedRooms.add(roomConverter.convertEntityToDto(existingRoom));
	            } else {
	                // New room, create and save it
	                Room newRoom = new Room();
	                newRoom.setRoomType(roomDto.getRoomType());
	                newRoom.setRoomName(roomDto.getRoomName());
	                newRoom.setPricePerDay(roomDto.getPricePerDay());
	                newRoom.setHotel(hotel);
	                roomRepository.save(newRoom);

	                updatedRooms.add(roomConverter.convertEntityToDto(newRoom));
	            }
	        }
	        // Commit the transaction
	    } catch (Exception e) {
	        // Rollback the transaction in case of an exception
	        throw new RuntimeException("Failed to update rooms for the hotel.", e);
	    }

	    return updatedRooms;
	}

	private boolean isDuplicateRoom(int hotelId, RoomDto roomDto) {
	    // Check if a room with the same name, type, and hotel ID already exists
	    Optional<Room> existingRoom = roomRepository.findByHotelAndRoomTypeAndRoomName(
	            hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId)),
	            roomDto.getRoomType(), roomDto.getRoomName());

	    return existingRoom.isPresent() && !existingRoom.get().getRoomId().equals(roomDto.getRoomId());
	}
}
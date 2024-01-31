package com.travel.serviceImpi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.travel.dto.RoomDto;
import com.travel.entity.Hotel;
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
	public RoomDto updateRoomById(Long roomId, Room room) {
		Room existingRoom = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFound("Room", "id", roomId));

		existingRoom.setRoomType(room.getRoomType());
		existingRoom.setRoomName(room.getRoomName());
		existingRoom.setPricePerDay(room.getPricePerDay());
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
	
	public void assignRoomToHotel(Long roomId, Long hotelId) {
		Hotel hotel = hotelRepository.findById(hotelId).orElseThrow( 
				()-> new ResourceNotFound("Hotel", "id", hotelId));
		Room room = roomRepository.findById(roomId).orElseThrow(
				()-> new ResourceNotFound("room", "id", roomId));
		
		room.setHotel(hotel);
		
		roomRepository.save(room);
		hotelRepository.save(hotel);
		
	}

}
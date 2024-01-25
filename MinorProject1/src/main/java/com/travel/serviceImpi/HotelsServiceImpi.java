package com.travel.serviceImpi;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.travel.exception.ResourceNotFound;
import com.travel.dto.HotelDto;
import com.travel.entity.Hotel;
import com.travel.repository.HotelsRepository;
import com.travel.service.HotelsService;
import com.travel.util.HotelsConverter;
@Service
public class HotelsServiceImpi implements HotelsService {

    @Autowired
    private HotelsRepository hotelsRepository;

    @Autowired
    private HotelsConverter hotelsConverter;

    @Override
    public HotelDto saveHotel(Hotel hotel) {
        // Check if a hotel with the same name, location, and mobile number already exists
        if (hotelAlreadyExists(hotel.getHotelName(), hotel.getHotelLocation(), hotel.getHotelMobileNumber())) {
            throw new DataIntegrityViolationException("A hotel with the same name, location, and mobile number already exists.");
        }

        // Save the hotel if the check passes
        hotelsRepository.save(hotel);

        // Convert and return the saved hotel as a DTO
        return hotelsConverter.convertEntityToDto(hotel);
    }

    @Override
    public HotelDto updateHotel(int hotelId, Hotel hotel) {
        Hotel existingHotel = hotelsRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));

        existingHotel.setHotelName(hotel.getHotelName());
        existingHotel.setHotelLocation(hotel.getHotelLocation());
        existingHotel.setAddress(hotel.getAddress());
        existingHotel.setHotelMobileNumber(hotel.getHotelMobileNumber());
        existingHotel.setManagerName(hotel.getManagerName());

        // Check if a hotel with the same name, location, and mobile number already exists
        if (hotelAlreadyExists(existingHotel.getHotelName(), existingHotel.getHotelLocation(), existingHotel.getHotelMobileNumber(), hotelId)) {
            throw new DataIntegrityViolationException("A hotel with the same name, location, and mobile number already exists.");
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

    // Additional method to check if a hotel with the same name, location, and mobile number already exists
    private boolean hotelAlreadyExists(String hotelName, String hotelLocation, String hotelMobileNumber) {
        return hotelsRepository.findByHotelNameAndHotelLocationAndHotelMobileNumber(hotelName, hotelLocation, hotelMobileNumber).isPresent();
    }

    // Additional method to check if a hotel with the same name, location, and mobile number already exists excluding a specific hotelId
    private boolean hotelAlreadyExists(String hotelName, String hotelLocation, String hotelMobileNumber, int excludeHotelId) {
        return hotelsRepository.findByHotelNameAndHotelLocationAndHotelMobileNumberAndHotelIdNot(hotelName, hotelLocation, hotelMobileNumber, excludeHotelId).isPresent();
    }

    @Override
    public void deleteHotelById(int hotelId) {
        Hotel hotel = hotelsRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFound("Hotel", "id", hotelId));
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
        if (hotelAlreadyExists(existingHotel.getHotelName(), existingHotel.getHotelLocation(), existingHotel.getHotelMobileNumber(), hotelId)) {
            throw new DataIntegrityViolationException("A hotel with the same name, location, and mobile number already exists.");
        }

        // Save the updated hotel
        hotelsRepository.save(existingHotel);

        // Convert and return the updated hotel as a DTO
        return hotelsConverter.convertEntityToDto(existingHotel);
    }


}

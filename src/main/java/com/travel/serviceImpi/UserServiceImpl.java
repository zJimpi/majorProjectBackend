
package com.travel.serviceImpi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travel.dto.CarBookingsDto;
import com.travel.dto.HotelDto;
import com.travel.dto.PackageDto;
import com.travel.dto.UserDto;
import com.travel.dto.bookingTableDto;
import com.travel.entity.BookingTable;
import com.travel.entity.CarBookings;
import com.travel.entity.User;
import com.travel.exception.ResourceNotFound;
import com.travel.exception.ResourceNotFoundException;
import com.travel.repository.BookingTableRepository;
import com.travel.repository.CarBookingsRepository;
import com.travel.repository.UserRepository;
import com.travel.service.UserService;
import com.travel.util.BookingTableConverter;
import com.travel.util.CarBookingsConverter;
import com.travel.util.UserConverter;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	BookingTableRepository bookingTableRepository;
	
	@Autowired
	BookingTableConverter bookingTableConverter;
	
	@Autowired
	CarBookingsRepository carBookingsRepository;
	
	@Autowired
	CarBookingsConverter carBookingsConverter;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	

	@Override
	public UserDto saveUser(User user) {
		// TODO Auto-generated method stub
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	    String encrypt = bcrypt.encode(user.getPassword());
	    user.setPassword(encrypt);
	    
	  
	    if(user.getUsername().toLowerCase().endsWith("admin"))
	    {
	    	user.setRole("admin");
	    }
	    else
	    {
	    	user.setRole("user");
	    }
	    userRepository.save(user);
	    return userConverter.convertEntityToDto(user);
	}

	@Override
	public UserDto changeUserPassword(String userName, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		User user = userRepository.findUserByUsername(userName);
		if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) 
		{
			String encrypt = bcrypt.encode(newPassword);
		    user.setPassword(encrypt);
			userRepository.save(user);
			return userConverter.convertEntityToDto(user);
		}
		return null;
		
	}

	@Override
	public void deleteUserById(Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", userId));
		userRepository.delete(user);
		
	}

	@Override
	public UserDto getUserByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findUserByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return userConverter.convertEntityToDto(user);
        }
        return null;
	}
	
	@Override
	public boolean checkAdmin(String username)
	{
		if(username.toLowerCase().endsWith("admin"))
	    {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}

	@Override
	public List<bookingTableDto> getPackageListByUsername(String Username) {
		// TODO Auto-generated method stub
		
		List<BookingTable> bookingTable = bookingTableRepository.findBookingsByUsername(Username);
		List<bookingTableDto> packageList = new ArrayList<>();

	    for (BookingTable booking : bookingTable) {
	        if (booking.getPackageId() != null) {
	            // Add the booking details to the list
	            packageList.add(bookingTableConverter.convertEntityToDto(booking));
	        }
	    }

	    return packageList;
		
	}

	@Override
	public List<bookingTableDto> getHotelListByUsername(String Username) {
		// TODO Auto-generated method stub
		List<BookingTable> bookingTable = bookingTableRepository.findBookingsByUsername(Username);
		List<bookingTableDto> HotelList = new ArrayList<>();

	    for (BookingTable booking : bookingTable) {
	        if (booking.getPackageId() == null) {
	            // Add the booking details to the list
	        	HotelList.add(bookingTableConverter.convertEntityToDto(booking));
	        }
	    }

	    return HotelList;
	}

	@Override
	public List<CarBookingsDto> getCarBookingDetailsByUsername(String Username) {
		// TODO Auto-generated method stub
		
		List<CarBookings> carBookingsList = carBookingsRepository.findCarBookingsByUsername(Username);
		List<CarBookingsDto> carBookingsDtoList = new ArrayList<>();

	    for (CarBookings carBookings : carBookingsList) {
	    	
	    	carBookingsDtoList.add(carBookingsConverter.convertEntityToDto(carBookings));
	    }
	    return carBookingsDtoList;
	}

	@Override
	public UserDto getUserByUsername(String userName) {
		// TODO Auto-generated method stub
		User user = userRepository.findUserByUsername(userName);
		return userConverter.convertEntityToDto(user);
	}
}
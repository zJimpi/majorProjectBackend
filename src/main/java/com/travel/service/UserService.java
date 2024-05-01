
package com.travel.service;

import java.util.List;

import com.travel.dto.CarBookingsDto;
import com.travel.dto.HotelDto;
import com.travel.dto.PackageDto;
import com.travel.dto.UserDto;
import com.travel.dto.bookingTableDto;
import com.travel.entity.User;

public interface UserService {
	
	UserDto saveUser(User user);
	
	UserDto changeUserPassword(String userName, String oldPassword, String newPassword);
	
	void deleteUserById(Long userId);
	
	UserDto getUserByUsernameAndPassword(String userName, String password);
	
	UserDto getUserByUsername(String userName);
	
	boolean checkAdmin(String userName);
	
	List<bookingTableDto> getPackageListByUsername(String Username);
	
	List<bookingTableDto> getHotelListByUsername(String Username);
	
	List<CarBookingsDto> getCarBookingDetailsByUsername(String Username);
	
}
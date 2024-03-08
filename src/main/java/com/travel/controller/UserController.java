
package com.travel.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travel.dto.CarBookingsDto;
import com.travel.dto.UserDto;
import com.travel.dto.bookingTableDto;
import com.travel.entity.User;
import com.travel.service.UserService;
import com.travel.util.UserConverter;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {


	@Autowired
	private UserService userService;

	@Autowired
	private UserConverter userConverter;
	
	@PostMapping("/saveUser")
	public UserDto saveUser(@Valid @RequestBody UserDto userDto) {
		// Convert the DTO to an entity
		final User user = userConverter.convertDtoToEntity(userDto);

		// Call the service to save the user and return the result
		return userService.saveUser(user);
	}
	
	@PutMapping("/changePasswordBy/{username}/{oldPassword}/{newPassword}")
	public UserDto changePassword(@PathVariable("username") String username, @PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword)
	{
		return userService.changeUserPassword(username, oldPassword, newPassword);
	}
	
	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId) {
		// Call the service to delete the user by its ID
		userService.deleteUserById(userId);

		// Return a response indicating successful deletion
		return new ResponseEntity<>(userId + " is deleted successfully!!", HttpStatus.OK);
	}
	
	@GetMapping("/getUserBy/{username}/{password}")
	public UserDto getUser(@PathVariable("username") String username, @PathVariable("password") String password)
	{
		return userService.getUserByUsernameAndPassword(username, password);
	}
	
	@GetMapping("/checkAdmin/{username}/{password}")
    public boolean checkAdmin(@PathVariable("username") String username, @PathVariable("password") String password) {
		boolean isAdmin = userService.checkAdmin(username);
        
		if (isAdmin) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	@GetMapping("/getPackageBookingsByUsername/{username}")
    public List<bookingTableDto> getPackageListByUsername(@PathVariable String username) {
        return userService.getPackageListByUsername(username);
    }

    @GetMapping("/getHotelBookingsByUsername/{username}")
    public List<bookingTableDto> getHotelListByUsername(@PathVariable String username) {
        return userService.getHotelListByUsername(username);
    }

    @GetMapping("/getCarBookingsByUsername/{username}")
    public List<CarBookingsDto> getCarBookingDetailsByUsername(@PathVariable String username) {
        return userService.getCarBookingDetailsByUsername(username);
    }
}
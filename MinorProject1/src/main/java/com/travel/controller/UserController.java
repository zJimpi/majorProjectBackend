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

import com.travel.dto.UserDto;
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

	// Endpoint for saving a user
	@PostMapping("/saveUser")
	public UserDto saveUser(@Valid @RequestBody UserDto userDto) {
		// Convert the DTO to an entity
		final User user = userConverter.convertDtoToEntity(userDto);

		// Call the service to save the user and return the result
		return userService.saveUser(user);
	}

	// Endpoint for deleting a user by its ID
	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable("id") long userId) {
		// Call the service to delete the user by its ID
		userService.deleteUserById(userId);

		// Return a response indicating successful deletion
		return new ResponseEntity<>(userId + " is deleted successfully!!", HttpStatus.OK);
	}

	// Endpoint for updating a user by its ID
	@PutMapping("/updateUser/{id}")
	public UserDto updateUser(@PathVariable("id") long userId, @RequestBody Map<String, Object> updates) {
		// Call the service to get the existing user by ID
		User existingUser = userService.getUserById(userId);

		// Update the user entity with the provided key/value pairs
		for (Map.Entry<String, Object> entry : updates.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			switch (key) {
			case "name":
				existingUser.setName((String) value);
				break;
			case "email":
				existingUser.setEmail((String) value);
				break;
			case "mobile":
				existingUser.setMobile((String) value);
				break;
			case "address":
				existingUser.setAddress((String) value);
				break;
			case "role":
				existingUser.setRole((String) value);
				break;
			case "password":
				// Handle password update (encrypt if necessary)
				BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
				String encryptedPassword = bcrypt.encode((String) value);
				existingUser.setPassword(encryptedPassword);
				break;
			// Add more cases for other fields as needed
			}
		}

		// Call the service to save the updated user
		userService.updateUser(userId, existingUser);

		// Convert and return the updated user as a DTO
		return userConverter.convertEntityToDto(existingUser);
	}

	// Endpoint for checking user credentials by email or phone number
	@PostMapping("/checkCredentials")
	public ResponseEntity<UserDto> checkCredentials(@RequestParam String value, @RequestParam String password) {
		// Call the service to check if the value exists in the user database and
		// validate the password
		UserDto userDto = userService.checkUserCredentials(value, password);

		if (userDto != null) {
			// If valid credentials, return the user details
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		} else {
			// If invalid credentials, return an appropriate response
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/changePassword/{id}")
	public ResponseEntity<String> changePassword(@PathVariable("id") long userId, @RequestParam String newPassword) {
		// Call the service to change the user password by ID
		userService.changeUserPassword(userId, newPassword);

		// Return a response indicating successful password change
		return new ResponseEntity<>("Password changed successfully for user with ID " + userId, HttpStatus.OK);
	}

	@GetMapping("/getUserById/{userId}")
	public UserDto getUserById(@PathVariable Long userId) {
		User user = userService.getUserById(userId);

		// Assuming userConverter has a method to convert User to UserDto
		return userConverter.convertEntityToDto(user);
	}

	// Endpoint for updating the profile picture of a user by ID
	@PutMapping("/updateProfilePicture/{id}")
	public ResponseEntity<String> updateProfilePicture(@PathVariable("id") long userId,
			@RequestParam("profilePicture") MultipartFile profilePicture) {
		// Call the service to update the profile picture
		userService.updateUserProfilePicture(userId, profilePicture);

		// Return a response indicating successful profile picture update
		return new ResponseEntity<>("Profile picture updated successfully for user with ID " + userId, HttpStatus.OK);
	}

	// Endpoint for getting the profile picture of a user by ID
	@GetMapping("/getProfilePicture/{id}")
	public ResponseEntity<byte[]> getProfilePictureById(@PathVariable("id") long userId) {
		// Call the service to get the profile picture by user ID
		byte[] profilePicture = userService.getUserProfilePicture(userId);

		// Check if the profile picture is found
		if (profilePicture != null && profilePicture.length > 0) {
			// Return the profile picture bytes with appropriate headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG); // Adjust based on your image type
			return new ResponseEntity<>(profilePicture, headers, HttpStatus.OK);
		} else {
			// Return a response indicating that the profile picture for the user with the
			// given ID was not found
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}

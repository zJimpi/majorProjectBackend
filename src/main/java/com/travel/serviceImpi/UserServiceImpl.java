
package com.travel.serviceImpi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travel.dto.UserDto;
import com.travel.entity.User;
import com.travel.exception.ResourceNotFound;
import com.travel.exception.ResourceNotFoundException;
import com.travel.repository.UserRepository;
//import com.travel.dto.UserDto;
//import com.travel.entity.User;
//import com.travel.repository.UserRepository;
import com.travel.service.UserService;
import com.travel.util.UserConverter;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
//    @Value("${profilePictures.upload.path}")
//    private String profilePicturesPath; // Set the path to a directory where you want to store profile pictures

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

	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public UserDto saveUser(User user) {
//	    // Check if a user with the same email or mobile already exists
//	    if (userAlreadyExists(user.getEmail(), user.getMobile())) {
//	        throw new DataIntegrityViolationException("A user with the same email or mobile already exists.");
//	    }
//
//	    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//	    String encrypt = bcrypt.encode(user.getPassword());
//	    user.setPassword(encrypt);
//
//	    // Check if the 'role' field is null or empty
//	    if (user.getRole() == null || user.getRole().isEmpty()) {
//	        // Set the default role to "user" if the 'role' field is null or empty
//	        user.setRole("user");
//	    } else if (!user.getRole().equalsIgnoreCase("user") && !user.getRole().equalsIgnoreCase("admin")) {
//	        // If the role is anything other than "user" or "admin", set it to "user"
//	        user.setRole("user");
//	    }
//
//	    userRepository.save(user);
//
//	    // Convert and return the saved user as a DTO
//	    return userConverter.convertEntityToDto(user);
//	}
//
//
//	@Override
//	public void deleteUserById(Long userId) {
//		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", userId));
//		userRepository.delete(user);
//	}
//	@Override
//	public UserDto updateUser(Long userId, User user) {
//	    User existingUser = userRepository.findById(userId)
//	            .orElseThrow(() -> new ResourceNotFound("User", "id", userId));
//
//	    existingUser.setName(user.getName());
//	    existingUser.setEmail(user.getEmail());
//	    existingUser.setMobile(user.getMobile());
//	    existingUser.setAddress(user.getAddress());
//
//	    // Check if the 'role' field in the provided user is not null or empty
//	    if (user.getRole() != null && !user.getRole().isEmpty()) {
//	        // Set the role in the existing user
//	        if (user.getRole().equalsIgnoreCase("user") || user.getRole().equalsIgnoreCase("admin")) {
//	            existingUser.setRole(user.getRole());
//	        } else {
//	            // If the provided role is anything other than "user" or "admin", set it to "user"
//	            existingUser.setRole("user");
//	        }
//	    } else {
//	        // If 'role' is null or empty in the provided user, set it to "user"
//	        existingUser.setRole("user");
//	    }
//
//	    // Check if a new password is provided
//	    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
//	        // Encrypt the new password before saving
//	        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//	        String encryptedPassword = bcrypt.encode(user.getPassword());
//	        existingUser.setPassword(encryptedPassword);
//	    }
//
//	    // Check if a user with the same email or mobile already exists
//	    if (userAlreadyExists(existingUser.getEmail(), existingUser.getMobile(), userId)) {
//	        throw new DataIntegrityViolationException("A user with the same email or mobile already exists.");
//	    }
//
//	    // Save the updated user if the check passes
//	    userRepository.save(existingUser);
//
//	    // Convert and return the updated user as a DTO
//	    return userConverter.convertEntityToDto(existingUser);
//	}
//
//
//
//	// Additional method to check if a user with the same email or mobile already
//	// exists
//	private boolean userAlreadyExists(String email, String mobile) {
//		return userRepository.findUserByEmail(email).isPresent() || userRepository.findUserByMobile(mobile).isPresent();
//	}
//
//	// Additional method to check if a user with the same email or mobile already
//	// exists excluding a specific userId
//	private boolean userAlreadyExists(String email, String mobile, Long excludeUserId) {
//		return userRepository.findUserByEmailAndIdNot(email, excludeUserId).isPresent()
//				|| userRepository.findUserByMobileAndIdNot(mobile, excludeUserId).isPresent();
//	}
//
//	// Implementation in User Service Impl
//	@Override
//	public UserDto checkUserCredentials(String value, String password) {
//		User user = userRepository.findByEmailOrMobile(value, value);
//
//		if (user != null) {
//			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//
//			// Use the matches method to compare the plaintext password with the hashed
//			// password
//			if (bcrypt.matches(password, user.getPassword())) {
//				// If user and password match, return the user details
//				return userConverter.convertEntityToDto(user);
//			}
//		}
//
//		// If not valid credentials, return null
//		return null;
//	}
//
//	@Override
//	public void changeUserPassword(long userId, String newPassword) {
//		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", userId));
//
//		// Encode the new password before saving it
//		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//		String encryptedPassword = bcrypt.encode(newPassword);
//		user.setPassword(encryptedPassword);
//
//		// Save the updated user with the new password
//		userRepository.save(user);
//	}
//
//    @Override
//    public User getUserById(long userId) {
//        return userRepository.findById(userId).orElse(null);
//    }
//
//
//
//    @Override
//    public byte[] getUserProfilePicture(long userId) {
//        // Construct the path to the user's profile picture
//        String profilePicturePath = Paths.get(profilePicturesPath, "user_" + userId + ".jpg").toString();
//
//        try {
//            // Read the profile picture file into bytes
//            Path path = Paths.get(profilePicturePath);
//            return Files.readAllBytes(path);
//        } catch (IOException e) {
//            // Handle the exception (e.g., log it) and return null or an empty byte array
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    
//
//    @Override
//    public void updateUserProfilePicture(long userId, MultipartFile profilePicture) {
//        // Check if the user exists
//        User existingUser = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//
//        // Save the profile picture
//        try {
//            String profilePicturePath = Paths.get(profilePicturesPath, "user_" + userId + ".jpg").toString();
//            Files.write(Paths.get(profilePicturePath), profilePicture.getBytes());
//            existingUser.setProfilePicture(profilePicturePath);
//
//            userRepository.save(existingUser);
//        } catch (IOException e) {
//            // Handle the exception (e.g., log it) or throw a custom exception
//            throw new RuntimeException("Failed to update profile picture for user with ID " + userId, e);
//        }
//    }
//
//
//	@Override
//	public List<UserDto> getUserList() {
//		// TODO Auto-generated method stub
//		return null;
//	}   
}
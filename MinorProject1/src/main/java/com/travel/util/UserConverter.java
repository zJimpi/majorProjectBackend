package com.travel.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.UserDto;
import com.travel.entity.User;

@Component
public class UserConverter {

    // Converts UserDto to User Entity
    public User convertDtoToEntity(UserDto userDto) {
        User user = new User();

        if (userDto != null) {
            BeanUtils.copyProperties(userDto, user);
        }

        return user;
    }

    // Converts User Entity to UserDto
    public UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();

        if (user != null) {
            BeanUtils.copyProperties(user, userDto, "password");
            userDto.setProfilePicture(user.getProfilePicture());
        }

        return userDto;
    }
}

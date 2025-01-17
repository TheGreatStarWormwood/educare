package com.cmps272.educare.mapper;

import com.cmps272.educare.dto.UserDto;
import com.cmps272.educare.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getPasswordHash(),
            user.getUserType()
        );
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User(); // Now directly instantiating User
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPasswordHash());
        user.setUserType(userDto.getUserType());
        return user;
    }
}

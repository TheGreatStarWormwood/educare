package com.cmps272.educare.service;

import java.util.List;
import com.cmps272.educare.dto.UserDto;
import com.cmps272.educare.exception.UserNotFoundException;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId) throws UserNotFoundException;

    List<UserDto> getUsers();

    void deleteUser(Long userId) throws UserNotFoundException;

    UserDto updateUser(UserDto userDto) throws UserNotFoundException;
}

package com.cmps272.educare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmps272.educare.dto.UserDto;
import com.cmps272.educare.entity.User;
import com.cmps272.educare.exception.UserNotFoundException;
import com.cmps272.educare.mapper.UserMapper;
import com.cmps272.educare.repository.UserRepository;
import com.cmps272.educare.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        user = userRepository.save(user);
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(UserMapper::mapToUserDto)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws UserNotFoundException {
        User user = userRepository.findById(userDto.getId())
                                  .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPasswordHash());
        user.setProfileDetails(userDto.getProfileDetails());
        user = userRepository.save(user);
        return UserMapper.mapToUserDto(user);
    }
}

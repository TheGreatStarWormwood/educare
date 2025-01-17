package com.cmps272.educare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cmps272.educare.dto.UserDto;
import com.cmps272.educare.service.UserService;
import com.cmps272.educare.exception.UserNotFoundException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        if (!isValidUserType(userDto.getUserType())) {
            return new ResponseEntity<>("Invalid userType. Must be 'Student' or 'Tutor'.", HttpStatus.BAD_REQUEST);
        }

        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    private boolean isValidUserType(String userType) {
        return "student".equalsIgnoreCase(userType) || "tutor".equalsIgnoreCase(userType);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId) throws UserNotFoundException {
        try {
            UserDto user = userService.getUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException userNotFoundException) {
            throw userNotFoundException;
        }
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/user")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        UserDto updatedUser = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}

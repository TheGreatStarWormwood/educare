package com.cmps272.educare.dto;

import com.cmps272.educare.entity.User;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private String userType;

    // Constructor, Getters, and Setters
    public UserDto(Long id, String name, String email, String passwordHash, String userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.userType = userType;
    }

    public UserDto() {}

    public static UserDto fromEntity(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPasswordHash(),  user.getUserType());
    }

    public static User toEntity(UserDto dto) {
        return new User(dto.getName(), dto.getEmail(), dto.getPasswordHash(),  dto.getUserType());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

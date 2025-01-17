package com.cmps272.educare.dto;

import com.cmps272.educare.entity.Student;

public class StudentDto {

    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private String profileDetails;
    private String StudentType;

    // Constructor, Getters, and Setters
    public StudentDto(Long id, String name, String email, String passwordHash, String profileDetails) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.profileDetails = profileDetails;
    }

    public StudentDto() {}

    public static StudentDto fromEntity(Student Student) {
        return new StudentDto(Student.getId(), Student.getName(), Student.getEmail(), Student.getPasswordHash(), Student.getProfileDetails());
    }

    public static Student toEntity(StudentDto dto) {
        return new Student(dto.getName(), dto.getEmail(), dto.getPasswordHash(), dto.getProfileDetails());
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

    public String getProfileDetails() {
        return profileDetails;
    }

    public void setProfileDetails(String profileDetails) {
        this.profileDetails = profileDetails;
    }

    public String getStudentType() {
        return StudentType;
    }

    public void setStudentType(String StudentType) {
        this.StudentType = StudentType;
    }
}

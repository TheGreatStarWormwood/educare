package com.cmps272.educare.dto;

import com.cmps272.educare.entity.Tutor;

public class TutorDto {

    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private String profileDetails;

    // Constructor, Getters, and Setters
    public TutorDto(Long id, String name, String email, String passwordHash, String profileDetails) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.profileDetails = profileDetails;
    }

    public TutorDto() {}

    public static TutorDto fromEntity(Tutor tutor) {
        return new TutorDto(tutor.getId(), tutor.getName(), tutor.getEmail(), tutor.getPasswordHash(), tutor.getProfileDetails());
    }

    public static Tutor toEntity(TutorDto dto) {
        return new Tutor(dto.getName(), dto.getEmail(), dto.getPasswordHash(), dto.getProfileDetails());
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
}

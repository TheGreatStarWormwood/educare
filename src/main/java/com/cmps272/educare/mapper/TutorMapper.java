package com.cmps272.educare.mapper;

import com.cmps272.educare.dto.TutorDto;
import com.cmps272.educare.entity.Tutor;

public class TutorMapper {

    public static TutorDto mapToTutorDto(Tutor tutor) {
        return new TutorDto(
            tutor.getId(),
            tutor.getName(),
            tutor.getEmail(),
            tutor.getPasswordHash(),
            tutor.getProfileDetails()
        );
    }

    public static Tutor mapToTutor(TutorDto tutorDto) {
        Tutor tutor = new Tutor(); // Now directly instantiating Tutor
        tutor.setName(tutorDto.getName());
        tutor.setEmail(tutorDto.getEmail());
        tutor.setPasswordHash(tutorDto.getPasswordHash());
        tutor.setProfileDetails(tutorDto.getProfileDetails());
        return tutor;
    }
}

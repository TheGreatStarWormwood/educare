package com.cmps272.educare.service;

import java.util.List;
import com.cmps272.educare.dto.TutorDto;
import com.cmps272.educare.exception.TutorNotFoundException;

public interface TutorService {

    TutorDto createTutor(TutorDto tutorDto);

    TutorDto getTutorById(Long tutorId) throws TutorNotFoundException;

    List<TutorDto> getTutors();

    void deleteTutor(Long tutorId) throws TutorNotFoundException;

    TutorDto updateTutor(TutorDto tutorDto) throws TutorNotFoundException;
}

package com.cmps272.educare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmps272.educare.dto.TutorDto;
import com.cmps272.educare.entity.Tutor;
import com.cmps272.educare.exception.TutorNotFoundException;
import com.cmps272.educare.mapper.TutorMapper;
import com.cmps272.educare.repository.TutorRepository;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public TutorDto createTutor(TutorDto tutorDto) {
        Tutor tutor = TutorMapper.mapToTutor(tutorDto);
        tutor = tutorRepository.save(tutor);
        return TutorMapper.mapToTutorDto(tutor);
    }

    @Override
    public TutorDto getTutorById(Long tutorId) throws TutorNotFoundException {
        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(() -> new TutorNotFoundException("Tutor not found"));
        return TutorMapper.mapToTutorDto(tutor);
    }

    @Override
    public List<TutorDto> getTutors() {
        List<Tutor> tutors = tutorRepository.findAll();
        return tutors.stream()
                    .map(TutorMapper::mapToTutorDto)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteTutor(Long tutorId) throws TutorNotFoundException {
        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(() -> new TutorNotFoundException("Tutor not found"));
        tutorRepository.delete(tutor);
    }

    @Override
    public TutorDto updateTutor(TutorDto tutorDto) throws TutorNotFoundException {
        Tutor tutor = tutorRepository.findById(tutorDto.getId())
                                  .orElseThrow(() -> new TutorNotFoundException("Tutor not found"));
        tutor.setName(tutorDto.getName());
        tutor.setEmail(tutorDto.getEmail());
        tutor.setPasswordHash(tutorDto.getPasswordHash());
        tutor.setProfileDetails(tutorDto.getProfileDetails());
        tutor = tutorRepository.save(tutor);
        return TutorMapper.mapToTutorDto(tutor);
    }
}

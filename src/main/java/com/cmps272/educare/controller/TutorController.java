package com.cmps272.educare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cmps272.educare.dto.TutorDto;
import com.cmps272.educare.service.TutorService;
import com.cmps272.educare.exception.TutorNotFoundException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping(value = "/Tutor")
    public ResponseEntity<?> createTutor(@RequestBody TutorDto tutorDto) {
        TutorDto createdTutor = tutorService.createTutor(tutorDto);
        return new ResponseEntity<>(createdTutor, HttpStatus.CREATED);
    }

    @GetMapping(value = "/Tutor/{tutorId}")
    public ResponseEntity<TutorDto> getTutorById(@PathVariable("tutorId") Long tutorId) throws TutorNotFoundException {
        try {
            TutorDto tutor = tutorService.getTutorById(tutorId);
            return new ResponseEntity<>(tutor, HttpStatus.OK);
        } catch (TutorNotFoundException tutorNotFoundException) {
            throw tutorNotFoundException;
        }
    }

    @GetMapping(value = "/Tutors")
    public ResponseEntity<List<TutorDto>> getTutors() {
        List<TutorDto> tutors = tutorService.getTutors();
        return new ResponseEntity<>(tutors, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Tutor/{tutorId}")
    public ResponseEntity<HttpStatus> deleteTutor(@PathVariable("tutorId") Long tutorId) throws TutorNotFoundException {
        tutorService.deleteTutor(tutorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/Tutor")
    public ResponseEntity<TutorDto> updateTutor(@RequestBody TutorDto tutorDto) throws TutorNotFoundException {
        TutorDto updatedTutor = tutorService.updateTutor(tutorDto);
        return new ResponseEntity<>(updatedTutor, HttpStatus.OK);
    }
}

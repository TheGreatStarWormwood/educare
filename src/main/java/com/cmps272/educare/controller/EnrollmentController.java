package com.cmps272.educare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cmps272.educare.dto.EnrollmentDto;
import com.cmps272.educare.service.EnrollmentService;
import com.cmps272.educare.exception.EnrollmentNotFoundException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Create an enrollment
    @PostMapping
    public ResponseEntity<?> createEnrollment(@RequestBody EnrollmentDto enrollmentDto) {
        EnrollmentDto createdEnrollment = enrollmentService.createEnrollment(enrollmentDto);
        return new ResponseEntity<>(createdEnrollment, HttpStatus.CREATED);
    }

    // Get an enrollment by ID
    @GetMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(@PathVariable("enrollmentId") Long enrollmentId) 
            throws EnrollmentNotFoundException {
        try {
            EnrollmentDto enrollment = enrollmentService.getEnrollmentById(enrollmentId);
            return new ResponseEntity<>(enrollment, HttpStatus.OK);
        } catch (EnrollmentNotFoundException ex) {
            throw ex;
        }
    }

    // Get all enrollments
    @GetMapping
    public ResponseEntity<List<EnrollmentDto>> getEnrollments() {
        List<EnrollmentDto> enrollments = enrollmentService.getEnrollments();
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    // Delete an enrollment
    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<HttpStatus> deleteEnrollment(@PathVariable("enrollmentId") Long enrollmentId) 
            throws EnrollmentNotFoundException {
        enrollmentService.deleteEnrollment(enrollmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update an enrollment
    @PutMapping
    public ResponseEntity<EnrollmentDto> updateEnrollment(@RequestBody EnrollmentDto enrollmentDto) 
            throws EnrollmentNotFoundException {
        EnrollmentDto updatedEnrollment = enrollmentService.updateEnrollment(enrollmentDto);
        return new ResponseEntity<>(updatedEnrollment, HttpStatus.OK);
    }
}

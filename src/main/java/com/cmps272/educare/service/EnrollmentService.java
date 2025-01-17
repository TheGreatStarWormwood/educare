package com.cmps272.educare.service;

import java.util.List;
import com.cmps272.educare.dto.EnrollmentDto;
import com.cmps272.educare.exception.EnrollmentNotFoundException;

public interface EnrollmentService {

    EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto);

    EnrollmentDto getEnrollmentById(Long enrollmentId) throws EnrollmentNotFoundException;

    List<EnrollmentDto> getEnrollments();

    void deleteEnrollment(Long enrollmentId) throws EnrollmentNotFoundException;

    EnrollmentDto updateEnrollment(EnrollmentDto enrollmentDto) throws EnrollmentNotFoundException;
}

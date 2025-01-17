package com.cmps272.educare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmps272.educare.dto.EnrollmentDto;
import com.cmps272.educare.entity.Course;
import com.cmps272.educare.entity.Enrollment;
import com.cmps272.educare.entity.Student;
import com.cmps272.educare.exception.EnrollmentNotFoundException;
import com.cmps272.educare.mapper.EnrollmentMapper;
import com.cmps272.educare.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = EnrollmentMapper.mapToEnrollment(enrollmentDto);
        enrollment = enrollmentRepository.save(enrollment);
        return EnrollmentMapper.mapToEnrollmentDto(enrollment);
    }

    @Override
    public EnrollmentDto getEnrollmentById(Long enrollmentId) throws EnrollmentNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
            .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));
        return EnrollmentMapper.mapToEnrollmentDto(enrollment);
    }

    @Override
    public List<EnrollmentDto> getEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        return enrollments.stream()
                    .map(EnrollmentMapper::mapToEnrollmentDto)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteEnrollment(Long enrollmentId) throws EnrollmentNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
            .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
    }

    @Override
    public EnrollmentDto updateEnrollment(EnrollmentDto enrollmentDto) throws EnrollmentNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentDto.getId())
            .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));
        enrollment.setStudent(new Student(enrollmentDto.getStudentId()));
        enrollment.setCourse(new Course(enrollmentDto.getCourseId()));
        enrollment = enrollmentRepository.save(enrollment);
        return EnrollmentMapper.mapToEnrollmentDto(enrollment);
    }
}

package com.cmps272.educare.mapper;

import com.cmps272.educare.dto.EnrollmentDto;
import com.cmps272.educare.entity.Course;
import com.cmps272.educare.entity.Enrollment;
import com.cmps272.educare.entity.Student;

public class EnrollmentMapper {

    public static EnrollmentDto mapToEnrollmentDto(Enrollment enrollment) {
        return new EnrollmentDto(enrollment.getId(), enrollment.getStudent().getId(), enrollment.getCourse().getId());
    }

    public static Enrollment mapToEnrollment(EnrollmentDto enrollmentDto) {
        return new Enrollment(new Student(enrollmentDto.getStudentId()), new Course(enrollmentDto.getCourseId()));
    }
}

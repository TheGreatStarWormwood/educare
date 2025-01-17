package com.cmps272.educare.dto;

import com.cmps272.educare.entity.Course;
import com.cmps272.educare.entity.Enrollment;
import com.cmps272.educare.entity.Student;

public class EnrollmentDto {

    private Long id;
    private Long studentId;
    private Long courseId;

    // Constructors, Getters, and Setters
    public EnrollmentDto(Long id, Long studentId, Long courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public EnrollmentDto() {}

    public static EnrollmentDto fromEntity(Enrollment enrollment) {
        return new EnrollmentDto(enrollment.getId(), enrollment.getStudent().getId(), enrollment.getCourse().getId());
    }

    public static Enrollment toEntity(EnrollmentDto dto) {
        return new Enrollment(new Student(dto.getStudentId()), new Course(dto.getCourseId()));  // Assuming both Student and Course exist
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}

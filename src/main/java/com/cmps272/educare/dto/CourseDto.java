package com.cmps272.educare.dto;

import com.cmps272.educare.entity.Course;
import com.cmps272.educare.entity.Tutor;

public class CourseDto {

    private Long id;
    private String name;
    private Long tutorId;

    // Constructors, Getters, and Setters
    public CourseDto(Long id, String name, Long tutorId) {
        this.id = id;
        this.name = name;
        this.tutorId = tutorId;
    }

    public CourseDto() {}

    public static CourseDto fromEntity(Course course) {
        return new CourseDto(course.getId(), course.getName(), course.getTutor().getId());
    }

    public static Course toEntity(CourseDto dto) {
        return new Course(dto.getName(), new Tutor(dto.getTutorId()));  // Assuming the Tutor exists
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

    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }
}

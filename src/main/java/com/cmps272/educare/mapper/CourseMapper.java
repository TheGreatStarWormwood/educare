package com.cmps272.educare.mapper;

import com.cmps272.educare.dto.CourseDto;
import com.cmps272.educare.entity.Course;
import com.cmps272.educare.entity.Tutor;

public class CourseMapper {

    public static CourseDto mapToCourseDto(Course course) {
        return new CourseDto(course.getId(), course.getName(), course.getTutor().getId());
    }

    public static Course mapToCourse(CourseDto courseDto) {
        return new Course(courseDto.getName(), new Tutor(courseDto.getTutorId()));
    }
}

package com.cmps272.educare.service;

import java.util.List;
import com.cmps272.educare.dto.CourseDto;
import com.cmps272.educare.exception.CourseNotFoundException;

public interface CourseService {

    CourseDto createCourse(CourseDto courseDto);

    CourseDto getCourseById(Long courseId) throws CourseNotFoundException;

    List<CourseDto> getCourses();

    void deleteCourse(Long courseId) throws CourseNotFoundException;

    CourseDto updateCourse(CourseDto courseDto) throws CourseNotFoundException;
}

package com.cmps272.educare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmps272.educare.dto.CourseDto;
import com.cmps272.educare.entity.Course;
import com.cmps272.educare.entity.Tutor;
import com.cmps272.educare.exception.CourseNotFoundException;
import com.cmps272.educare.mapper.CourseMapper;
import com.cmps272.educare.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = CourseMapper.mapToCourse(courseDto);
        course = courseRepository.save(course);
        return CourseMapper.mapToCourseDto(course);
    }

    @Override
    public CourseDto getCourseById(Long courseId) throws CourseNotFoundException {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        return CourseMapper.mapToCourseDto(course);
    }

    @Override
    public List<CourseDto> getCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                    .map(CourseMapper::mapToCourseDto)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteCourse(Long courseId) throws CourseNotFoundException {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        courseRepository.delete(course);
    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto) throws CourseNotFoundException {
        Course course = courseRepository.findById(courseDto.getId())
            .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        course.setName(courseDto.getName());
        course.setTutor(new Tutor(courseDto.getTutorId()));
        course = courseRepository.save(course);
        return CourseMapper.mapToCourseDto(course);
    }
}

package com.cmps272.educare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cmps272.educare.dto.CourseDto;
import com.cmps272.educare.service.CourseService;
import com.cmps272.educare.exception.CourseNotFoundException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/Course")
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto createdCourse = courseService.createCourse(courseDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/Course/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable("courseId") Long courseId) throws CourseNotFoundException {
        try {
            CourseDto course = courseService.getCourseById(courseId);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (CourseNotFoundException ex) {
            throw ex;
        }
    }

    @GetMapping(value = "/Courses")
    public ResponseEntity<List<CourseDto>> getCourses() {
        List<CourseDto> courses = courseService.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Course/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("courseId") Long courseId) throws CourseNotFoundException {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/Course")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto) throws CourseNotFoundException {
        CourseDto updatedCourse = courseService.updateCourse(courseDto);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }
}

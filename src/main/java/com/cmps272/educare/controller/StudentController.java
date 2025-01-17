package com.cmps272.educare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cmps272.educare.dto.StudentDto;
import com.cmps272.educare.service.StudentService;
import com.cmps272.educare.exception.StudentNotFoundException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StudentController {

    @Autowired
    private StudentService StudentService;

    @PostMapping(value = "/Student")
    public ResponseEntity<?> createStudent(@RequestBody StudentDto StudentDto) {
        StudentDto createdStudent = StudentService.createStudent(StudentDto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }


    @GetMapping(value = "/Student/{StudentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("StudentId") Long StudentId) throws StudentNotFoundException {
        try {
            StudentDto Student = StudentService.getStudentById(StudentId);
            return new ResponseEntity<>(Student, HttpStatus.OK);
        } catch (StudentNotFoundException StudentNotFoundException) {
            throw StudentNotFoundException;
        }
    }

    @GetMapping(value = "/Students")
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<StudentDto> Students = StudentService.getStudents();
        return new ResponseEntity<>(Students, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Student/{StudentId}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("StudentId") Long StudentId) throws StudentNotFoundException {
        StudentService.deleteStudent(StudentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/Student")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto StudentDto) throws StudentNotFoundException {
        StudentDto updatedStudent = StudentService.updateStudent(StudentDto);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }
}

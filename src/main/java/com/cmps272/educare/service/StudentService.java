package com.cmps272.educare.service;

import java.util.List;
import com.cmps272.educare.dto.StudentDto;
import com.cmps272.educare.exception.StudentNotFoundException;

public interface StudentService {

    StudentDto createStudent(StudentDto StudentDto);

    StudentDto getStudentById(Long StudentId) throws StudentNotFoundException;

    List<StudentDto> getStudents();

    void deleteStudent(Long StudentId) throws StudentNotFoundException;

    StudentDto updateStudent(StudentDto StudentDto) throws StudentNotFoundException;
}

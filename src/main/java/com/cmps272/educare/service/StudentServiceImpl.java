package com.cmps272.educare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmps272.educare.dto.StudentDto;
import com.cmps272.educare.entity.Student;
import com.cmps272.educare.exception.StudentNotFoundException;
import com.cmps272.educare.mapper.StudentMapper;
import com.cmps272.educare.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository StudentRepository;

    @Override
    public StudentDto createStudent(StudentDto StudentDto) {
        Student Student = StudentMapper.mapToStudent(StudentDto);
        Student = StudentRepository.save(Student);
        return StudentMapper.mapToStudentDto(Student);
    }

    @Override
    public StudentDto getStudentById(Long StudentId) throws StudentNotFoundException {
        Student Student = StudentRepository.findById(StudentId).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        return StudentMapper.mapToStudentDto(Student);
    }

    @Override
    public List<StudentDto> getStudents() {
        List<Student> Students = StudentRepository.findAll();
        return Students.stream()
                    .map(StudentMapper::mapToStudentDto)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteStudent(Long StudentId) throws StudentNotFoundException {
        Student Student = StudentRepository.findById(StudentId).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        StudentRepository.delete(Student);
    }

    @Override
    public StudentDto updateStudent(StudentDto StudentDto) throws StudentNotFoundException {
        Student Student = StudentRepository.findById(StudentDto.getId())
                                  .orElseThrow(() -> new StudentNotFoundException("Student not found"));
        Student.setName(StudentDto.getName());
        Student.setEmail(StudentDto.getEmail());
        Student.setPasswordHash(StudentDto.getPasswordHash());
        Student.setProfileDetails(StudentDto.getProfileDetails());
        Student = StudentRepository.save(Student);
        return StudentMapper.mapToStudentDto(Student);
    }
}

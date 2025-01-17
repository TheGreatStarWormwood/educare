package com.cmps272.educare.mapper;

import com.cmps272.educare.dto.StudentDto;
import com.cmps272.educare.entity.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student Student) {
        return new StudentDto(
            Student.getId(),
            Student.getName(),
            Student.getEmail(),
            Student.getPasswordHash(),
            Student.getProfileDetails()
        );
    }

    public static Student mapToStudent(StudentDto StudentDto) {
        Student Student = new Student(); // Now directly instantiating Student
        Student.setName(StudentDto.getName());
        Student.setEmail(StudentDto.getEmail());
        Student.setPasswordHash(StudentDto.getPasswordHash());
        Student.setProfileDetails(StudentDto.getProfileDetails());
        return Student;
    }
}

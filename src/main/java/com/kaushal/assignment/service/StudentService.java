package com.kaushal.assignment.service;

import com.kaushal.assignment.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student updateStudent(Student student);
    Student getStudentById(Long id);
    void deleteStudentById(Long id);
    Student getStudentByRandomString(String randomString);
}

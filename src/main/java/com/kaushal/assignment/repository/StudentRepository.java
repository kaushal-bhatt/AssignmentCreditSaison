package com.kaushal.assignment.repository;

import com.kaushal.assignment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByRandomString(String randomString);

}

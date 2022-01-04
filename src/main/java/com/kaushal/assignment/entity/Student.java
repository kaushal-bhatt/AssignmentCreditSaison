package com.kaushal.assignment.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="random_string",nullable = false)
    private String randomString;
    @Column
    private int count;
    @Column
    private String firstTime;
    @Column
    private String latestTime;



    public Student(String randomString, int count,String firstTime,String latestTime) {
        this.randomString = randomString;
        this.count = count;
        this.firstTime = firstTime;
        this.latestTime = latestTime;



    }

    public Student() {
    }
}

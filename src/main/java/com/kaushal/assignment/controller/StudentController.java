package com.kaushal.assignment.controller;

import com.kaushal.assignment.entity.Student;
import com.kaushal.assignment.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
public class StudentController {

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @Autowired
    private RestTemplate restTemplate;


    private StudentService studentService;

    //handler method to handle list students and return mode and view
    @GetMapping("/students/{randomString}")
    public String listStudents(@PathVariable String randomString,@ModelAttribute("student") Student student,Model model){
        String url ="https://lookup.binlist.net/"+student.getRandomString();
        System.out.println(url);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        log.info("response {}", response);
        log.info("response body {}", response.getBody());
        System.out.println(response.getBody());
        String res = response.getBody();
        JSONObject root = new JSONObject(res);
        JSONObject number = root.getJSONObject("number");
        int length = number.getInt("length");

        String verify="";
        if(length==student.getRandomString().length()){
            verify ="Card Number Is Valid";
        }
        else{
            verify = "Card Number Is Not Valid";
        }
        model.addAttribute("verify",verify);
        String scheme = root.getString("scheme");

        String type = root.getString("type");
        model.addAttribute("type",type);
        model.addAttribute("scheme",scheme);
        JSONObject bank= root.getJSONObject("bank");
        String bankName = bank.getString("name");
        model.addAttribute("bankName",bankName);
        model.addAttribute("students",studentService.getStudentByRandomString(student.getRandomString()));
        return "card";
    }

    @GetMapping("/students/stats")
    public String statistics(@ModelAttribute("stats") Student student,Model model){
        Student studentBean = studentService.getStudentByRandomString(student.getRandomString());
        model.addAttribute("stats",studentService.getAllStudents());
    return "students";
    }

    @GetMapping("/")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student",student);
            return "edit_student";
    }


    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student,Model model){

        Student studentBean = studentService.getStudentByRandomString(student.getRandomString());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh.mm aa");
        String date = df.format(new Date()).toString();
        if(studentBean!=null){
    studentBean.setCount(studentBean.getCount()+1);
    studentBean.setLatestTime(date);
            studentService.saveStudent(studentBean);
        }
        else {
            Student student1 = new Student();
            student1.setRandomString(student.getRandomString());
            student1.setCount(1);

            student1.setFirstTime(date);
            student1.setLatestTime(date);
            studentService.saveStudent(student1);
        }

        try {

            return "redirect:/students/"+ student.getRandomString();
        }
        catch (Exception e){
            log.error("Error {}", e.getMessage());
            return "DATA NOT FOUND";
        }

    }


}

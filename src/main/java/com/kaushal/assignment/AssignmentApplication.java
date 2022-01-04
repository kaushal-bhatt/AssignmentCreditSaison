package com.kaushal.assignment;

import com.kaushal.assignment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AssignmentApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(AssignmentApplication.class, args);

	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("system started");

	}

}

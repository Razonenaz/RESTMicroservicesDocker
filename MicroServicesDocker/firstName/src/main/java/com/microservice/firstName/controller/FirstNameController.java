package com.microservice.firstName.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.firstName.model.Student;
import com.microservice.firstName.service.FirstNameService;

@RestController
public class FirstNameController {

	@Autowired
	private FirstNameService service;

	@GetMapping("/student/get")
	public ArrayList<Student> getAll() {

		return service.getAll();
	}

	@GetMapping("/student/get/{id}")
	public Student getById(@PathVariable("id") String id) {

		return service.getFirstNameById(id);
	}

	@PostMapping("/student/add")
	public Student addFirstName(@RequestBody Student student) {

		return service.addFirstName(student);
	}

	@PutMapping("/student/update/{id}")
	public Student updateById(@RequestBody Student student, @PathVariable("id") String id) {
		service.updateFirstName(id, student);
		
		return service.updateFirstName(id, student);
	}

	@DeleteMapping("/student/delete/{id}")
	public String deleteFirstName(@PathVariable("id") String id) {
		return service.deleteFirstNameById(id);
	}
}

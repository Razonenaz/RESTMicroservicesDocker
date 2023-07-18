package com.microservice.middleName.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.middleName.model.Student;
import com.microservice.middleName.service.MiddleNameService;

@RestController
public class MiddleNameController {

	@Autowired
	private MiddleNameService service;

	@GetMapping(value = "/student/get")
	public ArrayList<Student> getAllMiddleAndLastName() {

		return service.getAllMiddleAndLastName();
	}

	@GetMapping(value = "/student/get/{id}")
	public Student getMiddleNameById(@PathVariable("id") String id) {

		return service.getMiddleNameById(id);
	}

	@PostMapping("/student/add")
	public String addMiddleName(@RequestBody Student student) {
		
		return service.addMiddleName(student);
	}

	@PutMapping("/student/update/{id}")
	public void updateMiddleName(@PathVariable("id") String id, @RequestBody Student student) {
		service.updateMiddleName(id, student);
	}

	@DeleteMapping("/student/delete/{id}")
	public void deleteMiddleName(@PathVariable("id") String id) {
		service.deleteMiddleNameById(id);
	}
}

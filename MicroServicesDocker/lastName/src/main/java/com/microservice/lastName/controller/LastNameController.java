package com.microservice.lastName.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.lastName.model.Student;
import com.microservice.lastName.service.LastNameService;

@RestController
public class LastNameController {

	@Autowired
	private LastNameService service;

	@GetMapping("/student/get")
	public ArrayList<Student> getAllLastName() {

		return service.getAllLastName();
	}

	@GetMapping("/student/get/{id}")
	public Student getLastNameById(@PathVariable("id") String id) {

		return service.getLastNameById(id);
	}

	@PostMapping("/student/add")
	public String addLastName(@RequestBody Student student) {

		return service.addLastName(student);
	}

	@PutMapping("/student/update/{id}")
	public void updateLastName(@PathVariable("id") String id, @RequestBody Student student) {
		service.updateLastName(id, student);
	}

	@DeleteMapping("/student/delete/{id}")
	public void deleteLastName(@PathVariable("id") String id) {
		service.deleteLastNameById(id);
	}

}

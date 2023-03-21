package com.microservice.lastName.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.lastName.service.LastNameService;

@RestController
public class LastNameController {

	@Autowired
	private LastNameService service;

	@GetMapping(value = "/student/get/{id}")
	public ResponseEntity<String> getById(@PathVariable("id") long id) {

		String lastName = service.getLastName(id);

		return new ResponseEntity<String>(lastName, HttpStatus.OK);
	}

	@PostMapping("/student/add/{id}/{lastName}")
	public ResponseEntity<String> addMiddleName(@PathVariable("id") long id,
			@PathVariable("lastName") String lastName) {

		String message = service.addLastName(id, lastName);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}/{lastName}")
	public ResponseEntity<String> updateById(@PathVariable("id") long id, @PathVariable("lastName") String lastName) {

		String message = service.updateLastName(id, lastName);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteMiddleName(@PathVariable("id") long id) {

		String message = service.deleteLastName(id);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}

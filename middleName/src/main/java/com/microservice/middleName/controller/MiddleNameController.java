package com.microservice.middleName.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.middleName.service.MiddleNameService;

@RestController
public class MiddleNameController {

	@Autowired
	private MiddleNameService service;

	@GetMapping(value = "/student/get/{id}")
	public ResponseEntity<String> getById(@PathVariable("id") long id) {

		String middleName = service.getMiddleName(id);

		return new ResponseEntity<String>(middleName, HttpStatus.OK);
	}

	@PostMapping("/student/add/{id}/{middleName}")
	public ResponseEntity<String> addMiddleName(@PathVariable("id") long id,
			@PathVariable("middleName") String middleName) {

		String message = service.addMiddleName(id, middleName);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}/{middleName}")
	public ResponseEntity<String> updateById(@PathVariable("id") long id,
			@PathVariable("middleName") String middleName) {

		String message = service.updateMiddleName(id, middleName);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<String> deleteMiddleName(@PathVariable("id") long id) {

		String message = service.deleteMiddleName(id);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}

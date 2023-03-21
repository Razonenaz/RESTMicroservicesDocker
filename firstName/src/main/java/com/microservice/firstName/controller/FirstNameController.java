package com.microservice.firstName.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.firstName.model.Student;
import com.microservice.firstName.service.FirstNameService;

@RestController
public class FirstNameController {

	@Autowired
	private FirstNameService service;

	@GetMapping(value = "/student/get/{id}")
	public ResponseEntity<Student> getById(@PathVariable("id") long id) {

		String firstName = service.getFirstName(id);
		String middleName = WebClient.create("http://localhost:8082").get()
				.uri(uriBuilder -> uriBuilder.path("/student/get/{id}").build(id)).retrieve().bodyToMono(String.class)
				.block();

		String lastName = WebClient.create("http://localhost:8083").get()
				.uri(uriBuilder -> uriBuilder.path("/student/get/{id}").build(id)).retrieve().bodyToMono(String.class)
				.block();

		Student student = new Student();
		student.setId(id);
		student.setFirstName(firstName);
		student.setMiddleName(middleName);
		student.setLastName(lastName);

		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@PostMapping("/student/add")
	public ResponseEntity<String> addFirstName(@RequestBody Student student) {

		String messageOne = service.addFirstName(student.getId(), student.getFirstName());
		String messageTwo = WebClient
				.create("http://localhost:8082").post().uri(uriBuilder -> uriBuilder
						.path("/student/add/{id}/{middleName}").build(student.getId(), student.getMiddleName()))
				.retrieve().bodyToMono(String.class).block();
		String messageThree = WebClient
				.create("http://localhost:8083").post().uri(uriBuilder -> uriBuilder
						.path("/student/add/{id}/{lastName}").build(student.getId(), student.getLastName()))
				.retrieve().bodyToMono(String.class).block();

		return new ResponseEntity<String>(messageOne + " " + messageTwo + " " + messageThree, HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<String> updateById(@RequestBody Student student, @PathVariable("id") long id) {

		String messageOne = service.updateFirstName(id, student.getFirstName());
		String messageTwo = WebClient.create("http://localhost:8082").put()
				.uri(uriBuilder -> uriBuilder.path("/update/{id}/{middleName}").build(id, student.getMiddleName()))
				.retrieve().bodyToMono(String.class).block();
		String messageThree = WebClient.create("http://localhost:8083").put()
				.uri(uriBuilder -> uriBuilder.path("/update/{id}/{lastName}").build(id, student.getLastName()))
				.retrieve().bodyToMono(String.class).block();

		return new ResponseEntity<String>(messageOne + " " + messageTwo + " " + messageThree, HttpStatus.OK);
	}

	@DeleteMapping(value = "/student/delete/{id}")
	public ResponseEntity<String> deleteFirstName(@PathVariable("id") long id) {

		String messageOne = service.deleteFirstName(id);
		String messageTwo = WebClient.create("http://localhost:8082").delete()
				.uri(uriBuilder -> uriBuilder.path("/delete/{id}/").build(id)).retrieve().bodyToMono(String.class)
				.block();
		String messageThree = WebClient.create("http://localhost:8083").delete()
				.uri(uriBuilder -> uriBuilder.path("/delete/{id}").build(id)).retrieve().bodyToMono(String.class)
				.block();

		return new ResponseEntity<String>(messageOne + " " + messageTwo + " " + messageThree, HttpStatus.OK);
	}
}

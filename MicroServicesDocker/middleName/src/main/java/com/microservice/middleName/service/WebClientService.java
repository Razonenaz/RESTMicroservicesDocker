package com.microservice.middleName.service;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.middleName.model.Student;

import reactor.core.publisher.Mono;

@Service
public class WebClientService {
	public ArrayList<Student> getAllLastName() {

		return (ArrayList<Student>) WebClient.create("http://lname:8083").get().uri("/student/get").retrieve()
				.bodyToMono(ArrayList.class).block();
	}

	public Student getLastNameById(String id) {
		Student student = WebClient.create("http://lname:8083").get()
				.uri(uriBuilder -> uriBuilder.path("/student/get/{id}").build(id)).retrieve().bodyToMono(Student.class)
				.block();

		return student;
	}

	public String addLastName(Student student) {
		String id = WebClient.create("http://lname:8083").post().uri("/student/add")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(student), Student.class).retrieve().bodyToMono(String.class).block();

		return id;

	}

	public void updateLastName(String id, Student student) {
		WebClient.create("http://lname:8083").put().uri("/student/update/" + id)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(student), Student.class).retrieve().bodyToMono(String.class).block();
	}

	public void deleteLastName(String id) {
		WebClient.create("http://lname:8083").delete().uri("/student/delete/" + id)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve().bodyToMono(String.class)
				.block();
	}
}

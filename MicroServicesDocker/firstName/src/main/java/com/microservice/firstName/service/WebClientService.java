package com.microservice.firstName.service;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.firstName.model.Student;

import reactor.core.publisher.Mono;

@Service
public class WebClientService {

	public ArrayList<Student> getAll() {
		ArrayList<Student> allMiddleAndLastName = WebClient.create("http://mname:8082").get().uri("/student/get")
				.retrieve().bodyToMono(ArrayList.class).block();

		return allMiddleAndLastName;
	}

	public Student getById(String id) {
		Student student = WebClient.create("http://mname:8082").get()
				.uri(uriBuilder -> uriBuilder.path("/student/get/{id}").build(id)).retrieve().bodyToMono(Student.class)
				.block();

		return student;
	}

	public String addFirstName(Student student) {
		String id = WebClient.create("http://mname:8082").post().uri("/student/add")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(student), Student.class).retrieve().bodyToMono(String.class).block();

		return id;
	}

	public void updateById(Student student, String id) {
		WebClient.create("http://mname:8082").put().uri("/student/update/" + id)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(student), Student.class).retrieve().bodyToMono(String.class).block();
	}

	public void deleteFirstName(String id) {
		WebClient.create("http://mname:8082").delete().uri("/student/delete/" + id)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve().bodyToMono(String.class)
				.block();
	}
}

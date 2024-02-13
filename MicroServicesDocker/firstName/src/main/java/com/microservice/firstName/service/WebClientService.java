package com.microservice.firstName.service;

import java.util.ArrayList;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.firstName.model.Student;

import reactor.core.publisher.Mono;

@Service
public class WebClientService {

	public ArrayList<Student> getAllMiddleAndLastName() {

		return WebClient.create("http://mname:8082").get().uri("/student/get").retrieve()
				.bodyToMono(new ParameterizedTypeReference<ArrayList<Student>>() {
				}).block();
	}

	public Student getMiddleAndLastNameById(String id) {

		return WebClient.create("http://mname:8082").get()
				.uri(uriBuilder -> uriBuilder.path("/student/get/{id}").build(id)).retrieve().bodyToMono(Student.class)
				.block();
	}

	public String addMiddleAndLastName(Student student) {

		return WebClient.create("http://mname:8082").post().uri("/student/add")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(student), Student.class).retrieve().bodyToMono(String.class).block();
	}

	public void updateMiddleAndLastName(Student student, String id) {
		WebClient.create("http://mname:8082").put().uri("/student/update/" + id)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(student), Student.class).retrieve().bodyToMono(String.class).block();
	}

	public void deleteMiddleAndLastName(String id) {
		WebClient.create("http://mname:8082").delete().uri("/student/delete/" + id)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve().bodyToMono(String.class)
				.block();
	}
}

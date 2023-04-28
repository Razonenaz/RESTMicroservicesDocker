package com.microservice.firstName.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

import reactor.core.publisher.Mono;

@RestController
public class FirstNameController {

    @Autowired
    private FirstNameService service;

    @GetMapping(value = "/student/get")
    public ArrayList<String> getAll() {
        ArrayList<String> allMiddleAndLastName = WebClient.create("http://localhost:8082")
            .get()
            .uri("/student/get")
            .retrieve()
            .bodyToMono(ArrayList.class)
            .block();

        return service.getAll(allMiddleAndLastName);
    }

    @GetMapping(value = "/student/get/{id}")
    public Student getById(@PathVariable("id") String id) {
        Student student = WebClient.create("http://localhost:8082")
            .get()
            .uri(uriBuilder -> uriBuilder.path("/student/get/{id}")
                .build(id))
            .retrieve()
            .bodyToMono(Student.class)
            .block();
        student.setFirstName(service.getFirstNameById(id)
            .getFirstName());
        student.setId(id);

        return student;
    }

    @PostMapping("/student/add")
    public String addFirstName(@RequestBody Student student) {

        String id = WebClient.create("http://localhost:8082")
            .post()
            .uri("/student/add")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Mono.just(student), Student.class)
            .retrieve()
            .bodyToMono(String.class)
            .block();
        service.addFirstName(id, student);

        return "Student added: " + student;
    }

    @PutMapping(value = "/student/update/{id}")
    public String updateById(@RequestBody Student student, @PathVariable("id") String id) {
        String result = WebClient.create("http://localhost:8082")
            .put()
            .uri("/student/update/" + id)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Mono.just(student), Student.class)
            .retrieve()
            .bodyToMono(String.class)
            .block();
        service.updateFirstName(id, student);

        return "Student updated: " + student;
    }

    @DeleteMapping(value = "/student/delete/{id}")
    public String deleteFirstName(@PathVariable("id") String id) {
        String result = WebClient.create("http://localhost:8082")
            .delete()
            .uri("/student/delete/" + id)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        return service.deleteFirstNameById(id);
    }
}

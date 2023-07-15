package com.microservice.middleName.controller;

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

import com.microservice.middleName.model.Student;
import com.microservice.middleName.service.MiddleNameService;

import reactor.core.publisher.Mono;

@RestController
public class MiddleNameController {

    @Autowired
    private MiddleNameService service;

    @GetMapping(value = "/student/get")
    public ArrayList<String> getAllMiddleAndLastName() {
        ArrayList<String> allLastName = WebClient.create("http://lname:8083")
            .get()
            .uri("/student/get")
            .retrieve()
            .bodyToMono(ArrayList.class)
            .block();

        return service.getAllMiddleAndLastName(allLastName);
    }

    @GetMapping(value = "/student/get/{id}")
    public Student getMiddleNameById(@PathVariable("id") String id) {

        Student student = WebClient.create("http://lname:8083")
            .get()
            .uri(uriBuilder -> uriBuilder.path("/student/get/{id}")
                .build(id))
            .retrieve()
            .bodyToMono(Student.class)
            .block();

        student.setMiddleName(service.getMiddleNameById(id)
            .getMiddleName());

        return student;
    }

    @PostMapping("/student/add")
    public String addMiddleName(@RequestBody Student student) {
        String id = WebClient.create("http://lname:8083")
            .post()
            .uri("/student/add")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Mono.just(student), Student.class)
            .retrieve()
            .bodyToMono(String.class)
            .block();
        service.addMiddleName(id, student);

        return id;

    }

    @PutMapping(value = "/student/update/{id}")
    public String updateMiddleName(@PathVariable("id") String id, @RequestBody Student student) {
        String result = WebClient.create("http://lname:8083")
            .put()
            .uri("/student/update/" + id)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Mono.just(student), Student.class)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        return service.updateMiddleName(id, student);
    }

    @DeleteMapping(value = "/student/delete/{id}")
    public String deleteMiddleName(@PathVariable("id") String id) {
        String result = WebClient.create("http://lname:8083")
            .delete()
            .uri("/student/delete/" + id)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        return service.deleteMiddleNameById(id);
    }
}

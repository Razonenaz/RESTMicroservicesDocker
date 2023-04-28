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

    @GetMapping(value = "/student/get")
    public ArrayList<String> getAllLastName() {

        return service.getAllLastName();
    }

    @GetMapping(value = "/student/get/{id}")
    public Student getLastNameById(@PathVariable("id") String id) {

        return service.getLastNameById(id);
    }

    @PostMapping("/student/add")
    public String addLastName(@RequestBody Student student) {

        return service.addLastName(student);
    }

    @PutMapping(value = "/student/update/{id}")
    public String updateLastName(@PathVariable("id") String id, @RequestBody Student student) {

        return service.updateLastName(id, student);
    }

    @DeleteMapping(value = "/student/delete/{id}")
    public String deleteLastName(@PathVariable("id") String id) {

        return service.deleteLastNameById(id);
    }

}

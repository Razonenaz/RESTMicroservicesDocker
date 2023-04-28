package com.microservice.lastName.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.lastName.model.Student;
import com.microservice.lastName.repository.LastNameRepository;

@Service
public class LastNameService {

    @Autowired
    private LastNameRepository repository;

    public ArrayList<String> getAllLastName() {
        List<Student> allStudents = repository.getAllLastName();
        ArrayList<String> allLastNames = new ArrayList<String>();
        for (Student student : allStudents) {
            allLastNames.add(student.getLastName());
        }

        return allLastNames;

    }

    public Student getLastNameById(String id) {

        return repository.getLastNameById(id);
    }

    public String addLastName(Student student) {

        return repository.addLastName(student.getLastName());
    }

    public String updateLastName(String id, Student student) {

        return repository.updateLastName(id, student.getLastName());
    }

    public String deleteLastNameById(String id) {

        return repository.deleteLastNameById(id);
    }
}

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

    public ArrayList<Student> getAllLastName() {
        List<Student> allStudents = repository.getAllLastName();
        ArrayList<Student> allLastNames = new ArrayList<Student>();
        for (Student student : allStudents) {
            allLastNames.add(student);
        }

        return allLastNames;
    }

    public Student getLastNameById(String id) {

        return repository.getLastNameById(id);
    }

    public String addLastName(Student student) {

        return repository.addLastName(student.getLastName());
    }

    public void updateLastName(String id, Student student) {
    	repository.updateLastName(id, student.getLastName());
    }

    public void deleteLastNameById(String id) {
    	repository.deleteLastNameById(id);
    }
}

package com.microservice.firstName.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.firstName.model.Student;
import com.microservice.firstName.repository.FirstNameRepository;

@Service
public class FirstNameService {

    @Autowired
    private FirstNameRepository repository;

    public ArrayList<String> getAll(ArrayList<String> allMiddleAndLastName) {
        List<Student> allFirstName = repository.getAllFirstNameAndId();
        ArrayList<String> allName = new ArrayList<String>();
        int i = 0;
        for (Student student : allFirstName) {
            allName.add(student.getId() + " " + student.getFirstName() + " " + allMiddleAndLastName.get(i));
            i++;
        }

        return allName;
    }

    public Student getFirstNameById(String id) {

        return repository.getFirstNameById(id);
    }

    public String addFirstName(String id, Student student) {

        return repository.addFirstName(id, student.getFirstName());
    }

    public String updateFirstName(String id, Student student) {

        return repository.updateFirstName(id, student.getFirstName());
    }

    public String deleteFirstNameById(String id) {

        return repository.deleteFirstName(id);
    }
}

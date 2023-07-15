package com.microservice.middleName.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.middleName.model.Student;
import com.microservice.middleName.repository.MiddleNameRepository;

@Service
public class MiddleNameService {

    @Autowired
    private MiddleNameRepository repository;

    public ArrayList<String> getAllMiddleAndLastName(ArrayList<String> allLastName) {
        List<Student> allMiddleName = repository.getAllMiddleName();
        ArrayList<String> allMiddleAndLastName = new ArrayList<String>();
        int i = 0;
        for (Student student : allMiddleName) {
            allMiddleAndLastName.add(student.getMiddleName() + " " + allLastName.get(i));
            i++;
        }
 
        return allMiddleAndLastName;
    }

    public Student getMiddleNameById(String id) {

        return repository.getMiddleNameById(id);
    }

    public String addMiddleName(String id, Student student) {

        return repository.addMiddleName(id, student.getMiddleName());
    }

    public String updateMiddleName(String id, Student student) {

        return repository.updateMiddleName(id, student.getMiddleName());
    }

    public String deleteMiddleNameById(String id) {

        return repository.deleteMiddleName(id);
    }
}

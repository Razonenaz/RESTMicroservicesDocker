package com.microservice.middleName.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.middleName.model.Student;
import com.microservice.middleName.repository.MiddleNameRepository;

@Service
public class MiddleNameService {

	@Autowired
	private MiddleNameRepository repository;

	@Autowired
	private WebClientService webClient;

	public ArrayList<Student> getAllMiddleAndLastName() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Student> allNames = mapper.convertValue(webClient.getAllMiddleAndLastName(),
				new TypeReference<ArrayList<Student>>() {
				});
		List<Student> allMiddleName = repository.getAllMiddleName();
		int i = 0;
		for (Student student : allMiddleName) {
			allNames.get(i).setMiddleName(student.getMiddleName());
			i++;
		}

		return allNames;
	}

	public Student getMiddleNameById(String id) {
		Student student = webClient.getMiddleNameById(id);
		student.setMiddleName(repository.getMiddleNameById(id).getMiddleName());

		return student;
	}

	public String addMiddleName(Student student) {
		String id = webClient.addMiddleName(student);
		repository.addMiddleName(id, student.getMiddleName());

		return id;
	}

	public void updateMiddleName(String id, Student student) {
		webClient.updateMiddleName(id, student);
		repository.updateMiddleName(id, student.getMiddleName());
	}

	public void deleteMiddleNameById(String id) {
		webClient.deleteMiddleName(id);
		repository.deleteMiddleName(id);
	}
}

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
		ArrayList<Student> allNames = mapper.convertValue(webClient.getAllLastName(),
				new TypeReference<ArrayList<Student>>() {
				});
		List<Student> allMiddleName = repository.getAllMiddleName();
		allNames.forEach(names -> {
			int i = 0;
			names.setMiddleName(allMiddleName.get(i).getMiddleName());
			i++;
		});

		return allNames;
	}

	public Student getMiddleNameById(String id) {
		Student student = webClient.getLastNameById(id);
		student.setMiddleName(repository.getMiddleNameById(id).getMiddleName());

		return student;
	}

	public String addMiddleName(Student student) {
		String id = webClient.addLastName(student);
		repository.addMiddleName(id, student.getMiddleName());

		return id;
	}

	public void updateMiddleName(String id, Student student) {
		webClient.updateLastName(id, student);
		repository.updateMiddleName(id, student.getMiddleName());
	}

	public void deleteMiddleNameById(String id) {
		webClient.deleteLastName(id);
		repository.deleteMiddleName(id);
	}
}

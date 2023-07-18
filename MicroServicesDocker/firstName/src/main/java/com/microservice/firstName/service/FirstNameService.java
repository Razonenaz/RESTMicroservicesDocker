package com.microservice.firstName.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.firstName.model.Student;
import com.microservice.firstName.repository.FirstNameRepository;

@Service
public class FirstNameService {

	@Autowired
	private FirstNameRepository repository;

	@Autowired
	private WebClientService webClient;

	public ArrayList<Student> getAll() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Student> allNames = mapper.convertValue(webClient.getAll(), new TypeReference<ArrayList<Student>>() {
		});
		List<Student> allFirstName = repository.getAllFirstName();
		int i = 0;
		for (Student student : allFirstName) {
			allNames.get(i).setFirstName(student.getFirstName());
			i++;
		}

		return allNames;
	}

	public Student getFirstNameById(String id) {
		Student student = webClient.getById(id);
		student.setFirstName(repository.getFirstNameById(id).getFirstName());
		student.setId(id);

		return student;
	}

	public String addFirstName(Student student) {
		String id = webClient.addFirstName(student);

		return repository.addFirstName(id, student.getFirstName());
	}

	public String updateFirstName(String id, Student student) {
		webClient.updateById(student, id);

		return repository.updateFirstName(id, student.getFirstName());
	}

	public void deleteFirstNameById(String id) {
		webClient.deleteFirstName(id);
		repository.deleteFirstName(id);
	}
}

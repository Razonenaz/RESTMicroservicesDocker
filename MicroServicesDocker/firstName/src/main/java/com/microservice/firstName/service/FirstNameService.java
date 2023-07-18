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
		ArrayList<Student> allNames = mapper.convertValue(webClient.getAllMiddleAndLastName(),
				new TypeReference<ArrayList<Student>>() {
				});
		List<Student> allFirstName = repository.getAllFirstName();
		allNames.forEach(names -> {
			int i = 0;
			names.setFirstName(allFirstName.get(i).getFirstName());
			i++;
		});

		return allNames;
	}

	public Student getFirstNameById(String id) {
		Student student = webClient.getMiddleAndLastNameById(id);
		student.setFirstName(repository.getFirstNameById(id).getFirstName());
		student.setId(id);

		return student;
	}

	public Student addFirstName(Student student) {
		String id = webClient.addMiddleAndLastName(student);

		return repository.addFirstName(id, student.getFirstName());
	}

	public Student updateFirstName(String id, Student student) {
		webClient.updateMiddleAndLastName(student, id);

		return repository.updateFirstName(id, student.getFirstName());
	}

	public String deleteFirstNameById(String id) {
		webClient.deleteMiddleAndLastName(id);
		repository.deleteFirstName(id);
		
		return "Deleted!";
	}
}

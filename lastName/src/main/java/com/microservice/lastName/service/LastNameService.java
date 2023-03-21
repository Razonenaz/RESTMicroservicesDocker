package com.microservice.lastName.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.microservice.lastName.model.Student;
import com.microservice.lastName.repository.IRepository;

@Service
public class LastNameService {

	@Autowired
	private IRepository repository;
	
	@Autowired
	private MongoTemplate mongoOperation;

	public String getLastName(long id) {

		return repository.findById(id).get().getLastName();
	}

	public String addLastName(long id, String lastName) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		query.fields().include("id");

		Update update = new Update();
		update.set("lastName", lastName);

		mongoOperation.updateFirst(query, update, Student.class);
		
		return "Last name saved: ";
	}

	public String updateLastName(long id, String lastName) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		query.fields().include("id");

		Update update = new Update();
		update.set("lastName", lastName);

		mongoOperation.updateFirst(query, update, Student.class);

		return ("Last name updated");
	}
	
	public String deleteLastName(long id) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		query.fields().include("id");

		Update update = new Update();
		update.set("lastName", null);

		mongoOperation.updateFirst(query, update, Student.class);

		return ("Last name deleted");
	}
}

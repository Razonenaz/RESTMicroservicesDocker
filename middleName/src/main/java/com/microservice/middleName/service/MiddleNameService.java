package com.microservice.middleName.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.microservice.middleName.model.Student;
import com.microservice.middleName.repository.IRepository;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class MiddleNameService {

	MongoCollection<org.bson.Document> collection;

	@Autowired
	private IRepository repository;

	@Autowired
	private MongoTemplate mongoOperation;

	public MiddleNameService() {
		var mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("Students");
		collection = database.getCollection("student");
	}

	public String getMiddleName(long id) {

		return repository.findById(id).get().getMiddleName();
	}

	public String addMiddleName(long id, String middleName) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		query.fields().include("id");

		Update update = new Update();
		update.set("middleName", middleName);

		mongoOperation.updateFirst(query, update, Student.class);

		return ("Middle name saved");
	}

	public String updateMiddleName(long id, String middleName) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		query.fields().include("id");

		Update update = new Update();
		update.set("middleName", middleName);

		mongoOperation.updateFirst(query, update, Student.class);

		return ("Middle name updated");
	}

	public String deleteMiddleName(long id) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		query.fields().include("id");

		Update update = new Update();
		update.set("middleName", null);

		mongoOperation.updateFirst(query, update, Student.class);

		return ("Middle name deleted");
	}
}

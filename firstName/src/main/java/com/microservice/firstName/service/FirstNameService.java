package com.microservice.firstName.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.microservice.firstName.model.Student;
import com.microservice.firstName.repository.IRepository;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class FirstNameService {

	MongoCollection<org.bson.Document> collection;

	@Autowired
	private IRepository repository;

	@Autowired
	private MongoTemplate mongoOperation;

	public FirstNameService() {
		var mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("Students");
		collection = database.getCollection("student");
	}

	public String getFirstName(long id) {

		return repository.findById(id).get().getFirstName();
	}

	public String addFirstName(long id, String firstName) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		query.fields().include("id");

		Update update = new Update();
		update.set("firstName", firstName);

		mongoOperation.updateFirst(query, update, Student.class);

		return ("First name saved");
	}

	public String updateFirstName(long id, String firstName) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		query.fields().include("id");

		Update update = new Update();
		update.set("firstName", firstName);

		mongoOperation.updateFirst(query, update, Student.class);

		return ("First name updated");
	}

	public String deleteFirstName(long id) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		query.fields().include("id");

		Update update = new Update();
		update.set("firstName", null);

		mongoOperation.updateFirst(query, update, Student.class);

		return ("First name deleted");
	}
}

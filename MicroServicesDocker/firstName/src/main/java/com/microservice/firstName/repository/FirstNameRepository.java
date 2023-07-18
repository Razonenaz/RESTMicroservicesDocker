package com.microservice.firstName.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.microservice.firstName.model.Student;
import com.mongodb.client.MongoCollection;

@Configuration
@PropertySource("classpath:application.properties")
@Repository
public class FirstNameRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	MongoCollection<Document> collection;

	public FirstNameRepository(@Value("${spring.data.mongodb.uri}") String mongoUri) {
		collection = DBContext.fetchCollection(mongoUri, "Students", "student");
	}

	public List<Student> getAllFirstName() {
		Query query = new Query();
		query.fields().include("firstName");

		return mongoTemplate.find(query, Student.class);
	}

	public Student getFirstNameById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		query.fields().include("firstName");

		return mongoTemplate.findOne(query, Student.class);
	}

	public String addFirstName(String id, String firstName) {
		Query query = new Query(Criteria.where("_id").is(id));
		Update update = new Update().set("firstName", firstName);
		mongoTemplate.updateFirst(query, update, Student.class);

		return "First name added";
	}

	public String updateFirstName(String id, String firstName) {
		Query query = new Query(Criteria.where("_id").is(id));
		Update update = new Update().set("firstName", firstName);
		mongoTemplate.updateFirst(query, update, Student.class);

		return "First name updated";
	}

	public String deleteFirstName(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		Update update = new Update().set("firstName", null);
		mongoTemplate.updateFirst(query, update, Student.class);

		return "Deleted";
	}
}

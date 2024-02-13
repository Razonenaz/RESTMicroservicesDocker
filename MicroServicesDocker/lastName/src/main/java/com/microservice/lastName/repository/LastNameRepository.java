package com.microservice.lastName.repository;

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

import com.microservice.lastName.model.Student;
import com.mongodb.client.MongoCollection;

@Configuration
@PropertySource("classpath:application.properties")
@Repository
public class LastNameRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	MongoCollection<Document> collection;

	public LastNameRepository(@Value("${spring.data.mongodb.uri}") String mongoUri) {
		collection = DBContext.fetchCollection(mongoUri, "Students", "student");
	}

	public List<Student> getAllLastName() {
		Query query = new Query();
		query.fields().include("lastName");

		return mongoTemplate.find(query, Student.class);
	}

	public Student getLastNameById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		query.fields().include("lastName");

		return mongoTemplate.findOne(query, Student.class);
	}

	public String addLastName(String lastName) {
		Student student = new Student();
		student.setLastName(lastName);

		return mongoTemplate.save(student).getId();
	}

	public String updateLastName(String id, String lastName) {
		Query query = new Query(Criteria.where("_id").is(id));
		Update update = new Update().set("lastName", lastName);

		mongoTemplate.updateFirst(query, update, Student.class);

		return "Last name updated";
	}

	public String deleteLastNameById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		Update update = new Update().set("lastName", null);

		mongoTemplate.updateFirst(query, update, Student.class);

		return "Deleted";
	}
}

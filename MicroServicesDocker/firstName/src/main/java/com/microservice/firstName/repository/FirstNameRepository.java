package com.microservice.firstName.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.microservice.firstName.model.Student;
import com.mongodb.client.MongoCollection;

@Repository
public class FirstNameRepository {

    @Autowired
    MongoTemplate mongoTemplate;
    MongoCollection<Document> collection;

    public FirstNameRepository() {
        collection = DBContext.fetchCollection(
                "mongodb://admin:password@localhost:27017/?authSource=admin&authMechanism=SCRAM-SHA-256", "Students",
                "student");
    }

    public List<Student> getAllFirstNameAndId() {
        Query query = new Query();
        query.fields()
            .include("firstName");
        query.fields()
            .include("_id");

        return mongoTemplate.find(query, Student.class);
    }

    public Student getFirstNameById(String id) {
        Query query = new Query(Criteria.where("_id")
            .is(id));
        query.fields()
            .include("firstName");

        return mongoTemplate.findOne(query, Student.class);
    }

    public String addFirstName(String id, String firstName) {
        Query query = new Query(Criteria.where("_id")
            .is(id));
        Update update = new Update().set("firstName", firstName);
        mongoTemplate.updateFirst(query, update, Student.class);

        return "First name added";
    }

    public String updateFirstName(String id, String firstName) {
        Query query = new Query(Criteria.where("_id")
            .is(id));
        Update update = new Update().set("firstName", firstName);
        mongoTemplate.updateFirst(query, update, Student.class);

        return "First name updated";
    }

    public String deleteFirstName(String id) {
        Query query = new Query(Criteria.where("_id")
            .is(id));
        Update update = new Update().set("firstName", null);
        mongoTemplate.updateFirst(query, update, Student.class);

        return "Deleted";
    }
}

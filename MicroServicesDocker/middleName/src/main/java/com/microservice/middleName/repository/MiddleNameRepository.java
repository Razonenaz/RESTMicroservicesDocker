package com.microservice.middleName.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.microservice.middleName.model.Student;
import com.mongodb.client.MongoCollection;

@Repository
public class MiddleNameRepository {

    @Autowired
    MongoTemplate mongoTemplate;
    MongoCollection<Document> collection;

    public MiddleNameRepository() {
        collection = DBContext.fetchCollection(
                "mongodb://admin:password@localhost:27017/?authSource=admin&authMechanism=SCRAM-SHA-256", "Students",
                "student");
    }

    public List<Student> getAllMiddleName() {
        Query query = new Query();
        query.fields()
            .include("middleName");

        return mongoTemplate.find(query, Student.class);
    }

    public Student getMiddleNameById(String id) {
        Query query = new Query(Criteria.where("_id")
            .is(id));
        query.fields()
            .include("middleName");

        return mongoTemplate.findOne(query, Student.class);
    }

    public String addMiddleName(String id, String middleName) {
        Query query = new Query(Criteria.where("_id")
            .is(id));
        Update update = new Update().set("middleName", middleName);
        mongoTemplate.updateFirst(query, update, Student.class);

        return "Middle name saved";
    }

    public String updateMiddleName(String id, String middleName) {
        Query query = new Query(Criteria.where("_id")
            .is(id));
        Update update = new Update().set("middleName", middleName);
        mongoTemplate.updateFirst(query, update, Student.class);

        return "Middle name updated";
    }

    public String deleteMiddleName(String id) {
        Query query = new Query(Criteria.where("_id")
            .is(id));
        Update update = new Update().set("middleName", null);
        mongoTemplate.updateFirst(query, update, Student.class);

        return "Deleted";
    }
}

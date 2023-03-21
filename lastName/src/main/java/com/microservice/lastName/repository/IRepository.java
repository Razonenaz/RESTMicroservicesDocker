package com.microservice.lastName.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.lastName.model.Student;

public interface IRepository extends MongoRepository<Student, Long> {

}

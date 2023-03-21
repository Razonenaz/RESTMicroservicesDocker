package com.microservice.firstName.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.firstName.model.Student;

public interface IRepository extends MongoRepository<Student, Long>{

}

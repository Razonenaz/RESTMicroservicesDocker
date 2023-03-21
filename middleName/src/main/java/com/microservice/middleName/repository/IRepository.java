package com.microservice.middleName.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.middleName.model.Student;

public interface IRepository extends MongoRepository<Student, Long> {

}

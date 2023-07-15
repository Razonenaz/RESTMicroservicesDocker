package com.microservice.middleName.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;


@Document
@Data
@NoArgsConstructor
public class Student {

    @Id
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
}

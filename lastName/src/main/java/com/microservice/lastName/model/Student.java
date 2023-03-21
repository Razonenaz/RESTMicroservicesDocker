package com.microservice.lastName.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
}

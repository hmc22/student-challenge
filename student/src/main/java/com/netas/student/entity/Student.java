package com.netas.student.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
public class Student implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 1, max = 255, message = "Name Cannot be empty")
    private String name;

    @Size(min = 1, max = 255, message = "Surname Cannot be empty")
    private String surname;

    @Size(min = 1, max = 255, message = "Mobile Phone Number Cannot be empty")
    private String mobilePhoneNumber;

    @Size(min = 1, max = 255, message = "City Cannot be empty")
    private String city;

    @Size(min = 1, max = 255, message = "District Cannot be empty")
    private String district;

    @Size(min = 1, max = 255, message = "Description Cannot be empty")
    private String description;
}

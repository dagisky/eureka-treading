package com.myRestful.rest.webservices.restfulwebservices.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @Size(min=7, message = "Name should have at least 7 characters")
    private String name;

    @Past
    private Date birthDate;

    private String username;

    @JsonIgnore
    private String password;

}

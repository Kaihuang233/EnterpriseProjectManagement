package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;

@Data
public class Employee {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "telnum")
    private String telnum;

    @JsonProperty(value = "mail")
    private String mail;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "employee_id")
    private int employee_id;

    @JsonProperty(value = "age")
    private Integer age;

    @JsonProperty(value = "post")
    private String post;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "salary")
    private Integer salary;

    @JsonProperty(value = "sex")
    private String sex;

    @JsonProperty(value = "user_id")
    private int user_id;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "entry_date")
    private Date entry_date;
}

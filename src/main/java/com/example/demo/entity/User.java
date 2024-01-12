package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    @JsonProperty(value = "user_id")
    private int user_id;

    @JsonProperty(value = "user_name")
    private String user_name;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "mail")
    private String mail;

    @JsonProperty(value = "telnum")
    private String telnum;

    @JsonProperty(value = "code")
    private String code;
}

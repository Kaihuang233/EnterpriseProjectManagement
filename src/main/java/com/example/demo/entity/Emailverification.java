package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Emailverification {
    @JsonProperty(value = "mail")
    private String mail;

    @JsonProperty(value = "code")
    private String code;
}

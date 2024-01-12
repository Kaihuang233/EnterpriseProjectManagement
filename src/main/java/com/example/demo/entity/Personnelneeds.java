package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Personnelneeds {

    @JsonProperty(value = "post")
    private String post;
    @JsonProperty(value = "number")
    private Integer number;
    @JsonProperty(value = "project_id")
    private Integer project_id;

    public Personnelneeds(Integer project_id, Integer number, String post) {
        this.project_id = project_id;
        this.number = number;
        this.post = post;
    }

}

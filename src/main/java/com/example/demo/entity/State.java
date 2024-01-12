package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.demo.entity.ArrangementException;
import lombok.Data;

@Data
public class State {
    private String msg;

    @JsonProperty(value = "user_id")
    private int user_id=-1;
    @JsonProperty(value = "type")
    private String type="";

    public State(String msg){
        this.msg = msg;
    }
    public State(String msg, int user_id){
        this.msg = msg;
        this.user_id = user_id;
    }
    public State(String msg, int user_id, String type){
        this.msg = msg;
        this.user_id = user_id;
        this.type = type;
    }
}

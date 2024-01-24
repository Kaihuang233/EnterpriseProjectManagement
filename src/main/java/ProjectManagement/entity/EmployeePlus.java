package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.Getter;

import java.sql.Date;

public class EmployeePlus extends Employee{
    @JsonProperty(value = "name")
    private Integer name;

    @JsonProperty(value = "date_json")
    private String date_json;

    @JsonProperty(value = "page")
    private String page;

    @JsonProperty(value = "date")
    private Date date;
}

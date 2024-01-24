package ProjectManagement.entity;

import ProjectManagement.controller.Transform;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeePlus extends Employee{

    @JsonProperty(value = "page")
    private String page;

    @JsonProperty(value = "date_json")
    private String date_json;

    @JsonProperty(value = "date")
    private Date date = Transform.trans(date_json);


}

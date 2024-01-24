package ProjectManagement.entity;

import ProjectManagement.controller.Transform;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;
@Data
public class Personnelarrangement {

    @JsonProperty(value = "employee_id")
    private int employee_id;

    @JsonProperty(value = "project_id")
    private int project_id;

    @JsonProperty(value = "user_id")
    private int user_id;

    @JsonProperty(value = "start_date_json")
    private String start_date_json;

    @JsonProperty(value = "start_date")
    private Date start_date = Transform.trans(start_date_json);

    @JsonProperty(value = "end_date_json")
    private String end_date_json;

    @JsonProperty(value = "end_date")
    private Date end_date = Transform.trans(end_date_json);


    public Personnelarrangement(int employee_id, Date start_date){
        this.employee_id = employee_id;
        this.start_date = start_date;
    }

    public Personnelarrangement(int employee_id, Date start_date, Date end_date){
        this.employee_id = employee_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    public Personnelarrangement(){}
}

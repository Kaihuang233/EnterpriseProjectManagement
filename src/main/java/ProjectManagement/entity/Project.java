package ProjectManagement.entity;

import ProjectManagement.controller.Transform;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.sql.Date;


@Data
public class Project {
    @JsonProperty(value = "project_id")
    private Integer project_id;

    @JsonProperty(value = "project_name")
    private String project_name;

    @JsonProperty(value = "customer_name")
    private String customer_name;

    @JsonProperty(value = "project_overview")
    private String project_overview;

    @JsonProperty(value = "start_date_json")
    private String start_date_json;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "start_date")
    private Date start_date = Transform.trans(start_date_json);

    @JsonProperty(value = "end_date_json")
    private String end_date_json;

    @JsonProperty(value = "end_date")
    private Date end_date = Transform.trans(end_date_json);

    @JsonProperty(value = "contract_amount")
    private Integer contract_amount;

    @JsonProperty(value = "user_id")
    private Integer user_id;

}

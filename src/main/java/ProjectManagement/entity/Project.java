package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.sql.Date;


@Data
public class Project {
    @JsonProperty(value = "project_id")
    private int project_id;

    @JsonProperty(value = "project_name")
    private String project_name;

    @JsonProperty(value = "customer_name")
    private String customer_name;

    @JsonProperty(value = "project_overview")
    private String project_overview;

    @JsonProperty(value = "start_date")
    private Date start_date;

    @JsonProperty(value = "end_date")
    private Date end_date;

    @JsonProperty(value = "contract_amount")
    private Integer contract_amount;

    @JsonProperty(value = "user_id")
    private Integer user_id;

}

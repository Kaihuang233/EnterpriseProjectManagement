package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;
@Data
public class RecentProject {

    @JsonProperty(value = "project_name")
    private String project_name;

    @JsonProperty(value = "start_date")
    private Date start_date;

    @JsonProperty(value = "contract_amount")
    private Integer contract_amount;

    @JsonProperty(value = "person_amount")
    private Integer person_amount;

    @JsonProperty(value = "project_amount")
    private Integer project_amount;

    @JsonProperty(value = "total_amount")
    private Integer total_amount;

}
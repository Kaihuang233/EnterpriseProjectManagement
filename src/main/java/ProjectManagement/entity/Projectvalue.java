package ProjectManagement.entity;

import ProjectManagement.controller.Transform;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;
@Data
public class Projectvalue {

    @JsonProperty(value = "project_id")
    private Integer project_id;

    @JsonProperty(value = "user_id")
    private Integer user_id;

    @JsonProperty(value = "value_id")
    private Integer value_id;

    @JsonProperty(value = "change_date_json")
    private String change_date_json;

    @JsonProperty(value = "change_date")
    private Date change_date = Transform.trans(change_date_json);

    @JsonProperty(value = "project_progress")
    private Integer project_progress;

}

package ProjectManagement.entity;

import ProjectManagement.controller.Transform;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;

@Data
public class Messageborad {
    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "project_id")
    private Integer project_id;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "user_name")
    private String user_name;

    @JsonProperty(value = "user_id")
    private Integer user_id;

    @JsonProperty(value = "comment_date_json")
    private String comment_date_json;
    @JsonProperty(value = "comment_date")
    private Date comment_date = Transform.trans(comment_date_json);
}

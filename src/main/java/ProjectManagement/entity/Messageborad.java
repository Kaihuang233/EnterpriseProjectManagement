package ProjectManagement.entity;

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

    @JsonProperty(value = "user_id")
    private Integer user_id;

    @JsonProperty(value = "comment_date")
    private Date comment_date;
}

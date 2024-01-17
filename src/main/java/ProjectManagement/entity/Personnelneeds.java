package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Personnelneeds {

    @JsonProperty(value = "post")
    private String post;

    @JsonProperty(value = "number")
    private Integer number;

    @JsonProperty(value = "project_id")
    private Integer project_id;


}

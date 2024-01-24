package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PersonnelRequirements {
    @JsonProperty(value = "personnelneeds")
    private List<Personnelneeds> personnelneeds;

    @JsonProperty(value = "user_id")
    private Integer user_id;

}

package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PersonnelarrangementPlus {
    @JsonProperty(value = "personnelarrangements")
    private List<Personnelarrangement> personnelarrangements;

    @JsonProperty(value = "user_id")
    private Integer user_id;
}

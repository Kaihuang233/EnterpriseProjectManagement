package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Value {
    @JsonProperty(value = "projectvalue")
    double projectvalue;

    @JsonProperty(value = "personvalue")
    double personvalue;

    @JsonProperty(value = "totalvalue")
    double totalvalue;
}

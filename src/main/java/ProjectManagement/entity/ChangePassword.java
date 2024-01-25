package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChangePassword {
    @JsonProperty(value = "user_id")
    private Integer user_id;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "new_password")
    private String new_password;

}

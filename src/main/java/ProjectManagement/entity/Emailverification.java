package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Emailverification {
    @JsonProperty(value = "mail")
    private String mail;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "telnum")
    private String telnum;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "user_id")
    private Integer user_id;
}

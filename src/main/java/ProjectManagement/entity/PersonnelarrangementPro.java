package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PersonnelarrangementPro extends Personnelarrangement{
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "post")
    private String post;

    public PersonnelarrangementPro(String name, String post, Personnelarrangement personnelarrangement){
        this.name = name;
        this.post = post;
        this.setStart_date(personnelarrangement.getStart_date());
        this.setEnd_date(personnelarrangement.getEnd_date());
        this.setEmployee_id(personnelarrangement.getEmployee_id());
    }

    public PersonnelarrangementPro(){}

}

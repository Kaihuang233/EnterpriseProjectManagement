package ProjectManagement.entity;

import ProjectManagement.controller.EmployeeController;
import ProjectManagement.controller.Transform;
import ProjectManagement.mapper.EmployeeMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeePlus extends Employee{

    @JsonProperty(value = "page")
    private String page;

    @JsonProperty(value = "date_json")
    private String date_json;

    @JsonProperty(value = "date")
    private Date date = Transform.trans(date_json);

    @JsonProperty(value = "user_name")
    private String user_name;
}

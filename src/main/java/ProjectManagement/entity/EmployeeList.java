package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeList {
    @JsonProperty(value = "post")
    private String post;

    @JsonProperty(value = "employees")
    private List<Employee> employees;
}

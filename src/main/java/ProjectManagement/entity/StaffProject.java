package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;
import java.util.List;
@Data
public class StaffProject {
    @JsonProperty(value = "ProjectList")
    private List<ProjectList> ProjectList;

    @JsonProperty(value = "Days")
    private int Days;

    @JsonProperty(value = "Month")
    private String Month;

    public void setProjectList(List<ProjectManagement.entity.ProjectList> projectList) {
        this.ProjectList = projectList;
    }
}

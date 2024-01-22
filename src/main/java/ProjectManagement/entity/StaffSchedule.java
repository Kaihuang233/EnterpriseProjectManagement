package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StaffSchedule {
    @JsonProperty(value = "StaffName")
    private String StaffName;

    @JsonProperty(value = "StaffProject")
    private List<StaffProject> StaffProject;

    public void setStaffProject(List<ProjectManagement.entity.StaffProject> staffProject) {
        this.StaffProject = staffProject;
    }

}

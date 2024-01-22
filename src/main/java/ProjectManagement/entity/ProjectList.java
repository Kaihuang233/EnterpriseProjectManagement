package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ProjectList {

    @JsonProperty(value = "project_name")
    private String project_name;

    @JsonProperty(value = "DayList")
    private List<String> DayList;

    public void DayList(List<String> getdays) {
        this.DayList = getdays;
    }
}

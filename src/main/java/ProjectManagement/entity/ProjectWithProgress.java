package ProjectManagement.entity;

import ProjectManagement.controller.Transform;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


import java.sql.Date;

public class ProjectWithProgress extends Project{
    @JsonProperty(value = "project_progress")
    private String project_progress;

    @Getter
    @JsonProperty(value = "page")
    private String page;

    public void setProject_progress(int project_progress){
        this.project_progress = String.valueOf(project_progress);
    }

    public void setPage(String page){
        this.page = page;
    }

}

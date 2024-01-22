package ProjectManagement.entity;

import ProjectManagement.controller.Transform;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.sql.Date;

@Data
public class MyMap{
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "project_name")
    private String project_name;

    @JsonProperty(value = "start_date")
    private String start_date_json;

    private Date start_date = Transform.trans(start_date_json);

    @JsonProperty(value = "end_date_json")
    private String end_date_json;
    @JsonProperty(value = "end_date")
    private Date end_date = Transform.trans(end_date_json);

    public MyMap(String name, String project_name, Date startDate, Date endDate) {
        this.name = name;
        this.project_name = project_name;
        this.start_date = startDate;
        this.end_date = endDate;
    }

    public MyMap() {
    }
}


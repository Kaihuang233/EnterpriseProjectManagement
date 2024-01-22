package ProjectManagement.entity;

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
    private Date start_date;

    @JsonProperty(value = "end_date")
    private Date end_date;

    public MyMap(String name, String project_name, Date startDate, Date endDate) {
        this.name = name;
        this.project_name = project_name;
        this.start_date = startDate;
        this.end_date = endDate;
    }

    public MyMap() {
    }
}


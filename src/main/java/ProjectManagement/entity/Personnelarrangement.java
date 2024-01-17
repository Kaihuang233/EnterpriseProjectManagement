package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;
@Data
public class Personnelarrangement {

    @JsonProperty(value = "employee_id")
    private int employee_id;

    @JsonProperty(value = "project_id")
    private int project_id;

    @JsonProperty(value = "start_date")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date start_date;

    @JsonProperty(value = "end_date")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date end_date;
}

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

    public Personnelarrangement(int employee_id, Date start_date){
        this.employee_id = employee_id;
        this.start_date = start_date;
    }

    public Personnelarrangement(int employee_id, Date start_date, Date end_date){
        this.employee_id = employee_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    Personnelarrangement(){}
}

package ProjectManagement.service;

import ProjectManagement.entity.Project;
import ProjectManagement.entity.Projectvalue;
import ProjectManagement.entity.State;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ProjectValueService {
    double ComputeProject(int amount, int current_progress, int progress);//计算项目支撑产值
    Map<Integer,Double> ComputePerson(int salary, Date start_date, Date end_date);//计算人员支撑产值
    State newprogress(Projectvalue projectvalue);//提交项目进度
    State getProjectValue(Projectvalue projectvalue);//计算某项目的各月产值
}

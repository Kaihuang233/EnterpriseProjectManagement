package ProjectManagement.service;

import ProjectManagement.entity.Personnelarrangement;
import ProjectManagement.entity.Project;
import ProjectManagement.entity.Projectvalue;
import ProjectManagement.entity.State;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ProjectValueService {
    int ComputeProject(int amount, int current_progress, int progress);//计算项目支撑产值
    Map<String,Integer> ComputePerson(int salary, Date start_date, Date end_date);//计算人员支撑产值
    State newprogress(Projectvalue projectvalue);//提交项目进度

    State getValue(Projectvalue projectvalue);//计算某项目的各月产值

    State getpersonValue(Projectvalue projectvalue);//获取该项目各月的人员产值

    State getprojectValue(Projectvalue projectvalue);//获取该项目各月的项目支撑产值

    State getannualValue(Personnelarrangement personnelarrangement);//获取某年总产值

    State getannualPersonValue(Personnelarrangement personnelarrangement);//获取某年人员支撑产值

    State getannualProjectValue(Personnelarrangement personnelarrangement);//获取某年项目支撑产值
}

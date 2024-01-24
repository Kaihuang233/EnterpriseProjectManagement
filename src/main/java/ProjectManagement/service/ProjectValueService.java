package ProjectManagement.service;

import ProjectManagement.entity.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ProjectValueService {
    int ComputeProject(int amount, int current_progress, int progress);//计算项目支撑产值
    Map<String,Integer> ComputePerson(int salary, Date start_date, Date end_date);//计算人员支撑产值
    NewState newprogress(Projectvalue projectvalue);//提交项目进度

    NewState getValue(Projectvalue projectvalue);//计算某项目的各月产值

    NewState getpersonValue(Projectvalue projectvalue);//获取该项目各月的人员产值

    NewState getprojectValue(Projectvalue projectvalue);//获取该项目各月的项目支撑产值

    NewState getannualValue(Personnelarrangement personnelarrangement);//获取某年总产值

    NewState getannualPersonValue(Personnelarrangement personnelarrangement);//获取某年人员支撑产值

    NewState getannualProjectValue(Personnelarrangement personnelarrangement);//获取某年项目支撑产值

    NewState getRecentpro(RecentProject recentProject);//按名称筛选项目
}

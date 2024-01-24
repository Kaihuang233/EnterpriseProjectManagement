package ProjectManagement.service;

import ProjectManagement.entity.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PersonnelArrangeService {
    NewState newArrangement(List<Personnelarrangement> personnelarrangements);//新增人员安排

    NewState Arrangement(Personnelarrangement personnelarrangement);//获取某项目的人员安排列表

    NewState DelEmployee(Employee employee);//将某员工从项目中删除
}

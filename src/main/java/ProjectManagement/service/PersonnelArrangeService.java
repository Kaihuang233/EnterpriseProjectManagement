package ProjectManagement.service;

import ProjectManagement.entity.Personnelarrangement;
import ProjectManagement.entity.Project;
import ProjectManagement.entity.State;
import org.springframework.web.bind.annotation.RequestBody;

public interface PersonnelArrangeService {
    State newArrangement(@RequestBody Personnelarrangement[] personnelarrangements);//新增人员安排
    State Arrangement(Personnelarrangement personnelarrangement);//获取某项目的人员安排列表
}

package ProjectManagement.service;

import ProjectManagement.entity.NewState;
import ProjectManagement.entity.Personnelneeds;
import ProjectManagement.entity.Project;
import ProjectManagement.entity.State;
import org.springframework.web.bind.annotation.RequestBody;

public interface PersonnelneedsService {
    NewState NewPersonnelneeds(@RequestBody Personnelneeds[] personnelneeds);//新建人员需求
    NewState GetPersonnelneeds(Project project);//获取该项目id的人员需求列表
}

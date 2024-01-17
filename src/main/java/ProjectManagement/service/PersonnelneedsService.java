package ProjectManagement.service;

import ProjectManagement.entity.Personnelneeds;
import ProjectManagement.entity.State;
import org.springframework.web.bind.annotation.RequestBody;

public interface PersonnelneedsService {
    State NewPersonnelneeds(@RequestBody Personnelneeds[] personnelneeds);//新建人员需求
    State GetPersonnelneeds(int project_id);//获取该项目id的人员需求列表
}

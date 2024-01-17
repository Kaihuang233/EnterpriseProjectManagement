package ProjectManagement.service;

import ProjectManagement.entity.Project;
import ProjectManagement.entity.State;

public interface ProjectManagementService {
    State newproject(Project project);//新建项目
    State projectsearch(Project project);//项目信息查询
    State ChangeProject(Project project);//项目信息修改
}

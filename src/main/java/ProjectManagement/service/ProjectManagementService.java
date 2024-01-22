package ProjectManagement.service;

import ProjectManagement.entity.NewState;
import ProjectManagement.entity.Project;
import ProjectManagement.entity.State;

public interface ProjectManagementService {
    NewState newproject(Project project);//新建项目
    NewState projectsearch(Project project);//项目信息查询
    NewState ChangeProject(Project project);//项目信息修改
}

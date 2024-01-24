package ProjectManagement.service;

import ProjectManagement.entity.NewState;
import ProjectManagement.entity.Project;
import ProjectManagement.entity.ProjectWithProgress;
import ProjectManagement.entity.State;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProjectManagementService {
    NewState newproject(Project project);//新建项目

    NewState projectsearch(Project project);//项目信息查询

    NewState ChangeProject(Project project);//项目信息修改

    NewState SearchProject(ProjectWithProgress project);//查询项目列表

    NewState startproject(Project project);//关停、激活项目
}

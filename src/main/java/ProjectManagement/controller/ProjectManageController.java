package ProjectManagement.controller;

import ProjectManagement.entity.Project;
import ProjectManagement.entity.Projectvalue;
import ProjectManagement.entity.State;
import ProjectManagement.mapper.ProjectMapper;
import ProjectManagement.mapper.ProjectValueMapper;
import ProjectManagement.service.ProjectManagementService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")//跨域
@RestController
public class ProjectManageController implements ProjectManagementService {
    @Resource
    private ProjectMapper projectMapper;//实例化对象并注入
    @Resource
    ProjectValueMapper projectValueMapper;//实例化对象并注入
    //新建项目
    @RequestMapping(value = "/Newproject", method = RequestMethod.POST)
    public State newproject(Project project) {
        if (project.getContract_amount()==null)
            return new State("请保证信息输入完整");
        else {
            try {
                projectMapper.CreateProject(project);
                return new State(project.getProject_name() + "创建成功", project.getUser_id());
            }catch (Exception e){
                return new State("项目名称重复");
            }
        }
    }
    //项目信息查询
    @RequestMapping(value = "/ProjectSearch", method = RequestMethod.POST)
    public State projectsearch(Project project) {
        return new State("该项目信息如下", projectMapper.GetProject(project));
    }

    //项目信息修改
    @RequestMapping(value = "/Changeproject", method = RequestMethod.POST)
    public State ChangeProject(Project project) {
        projectMapper.UpdateProject(project);
        return new State("项目信息修改成功", project.getUser_id());
    }



}

package ProjectManagement.controller;

import ProjectManagement.entity.NewState;
import ProjectManagement.entity.Project;
import ProjectManagement.entity.Projectvalue;
import ProjectManagement.entity.State;
import ProjectManagement.mapper.ProjectMapper;
import ProjectManagement.mapper.ProjectValueMapper;
import ProjectManagement.service.ProjectManagementService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@CrossOrigin(origins = "*")//跨域
@RestController
public class ProjectManageController implements ProjectManagementService {
    @Resource
    private ProjectMapper projectMapper;//实例化对象并注入

    private Map<String, Object> map;

    //新建项目
    @RequestMapping(value = "/Newproject", method = RequestMethod.POST)
    public NewState newproject(@RequestBody(required = false) Project project) {
        map = new TreeMap<>();
        if (project.getContract_amount()==null)
            return new NewState("401", "请保证信息输入完整");
        else {
            try {
                projectMapper.CreateProject(project);
                map.put("user_id", project.getUser_id());
                return new NewState("200",project.getProject_name() + "创建成功", map);
            }catch (Exception e){
                System.out.println(e);
                return new NewState("400","出现未知错误");
            }
        }
    }
    //项目信息查询
    @RequestMapping(value = "/ProjectSearch", method = RequestMethod.POST)
    public NewState projectsearch(@RequestBody(required = false) Project project) {
        map = new TreeMap<>();
        map.put("Project", projectMapper.GetProject(project));
        return new NewState("200", "该项目信息如下", map);
    }

    //项目信息修改
    @RequestMapping(value = "/Changeproject", method = RequestMethod.POST)
    public NewState ChangeProject(@RequestBody(required = false) Project project) {
        map = new TreeMap<>();
        projectMapper.UpdateProject(project);

        map.put("user_id", project.getUser_id());
        return new NewState("200", "项目信息修改成功", map);
    }



}

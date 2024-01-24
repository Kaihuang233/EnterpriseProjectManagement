package ProjectManagement.controller;

import ProjectManagement.entity.*;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.ProjectMapper;
import ProjectManagement.mapper.ProjectValueMapper;
import ProjectManagement.service.ProjectManagementService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.lang.Math.max;


@CrossOrigin(origins = "*")//跨域
@RestController
public class ProjectManageController implements ProjectManagementService {
    @Resource
    private ProjectMapper projectMapper;//实例化对象并注入

    @Resource
    private EmployeeMapper employeeMapper;//实例化对象并注入
    private Map<String, Object> map;

    //新建项目
    @RequestMapping(value = "/Newproject", method = RequestMethod.POST)
    public NewState newproject(@RequestBody(required = false) Project project) {
        map = new TreeMap<>();
        if (project.getContract_amount()==null)
            return new NewState("401", "请保证信息输入完整");
        else if (!projectMapper.GetProjectName(project.getProject_name()).isEmpty()){
            return new NewState("401", "项目名称重复");
        }else{
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
    //查询项目列表
    @RequestMapping(value = "/SearchProject", method = RequestMethod.POST)
    public NewState SearchProject(@RequestBody(required = false) ProjectWithProgress project) {
        map = new TreeMap<>();
        try {
            List<ProjectWithProgress> l1;
            List<ProjectWithProgress> ll1 = new ArrayList<>();//存符合条件项目的信息
            List<Projectvalue> l2;
            if (Objects.equals(project.getProject_name(), "")) {//为空时搜索所有
                l1 = projectMapper.GetProjectList1();
                for (ProjectWithProgress p : l1) {
                    l2 = projectMapper.GetProjectValue(p.getProject_id());
                    int temp = 0;
                    for (Projectvalue pv : l2) {//获取最新进度
                        temp = pv.getProject_progress();
                        temp = max(temp, pv.getProject_progress());
                    }
                    p.setProject_progress(temp);//设置最新进度
                    ll1.add(p);
                }
            }else{
                l1 = projectMapper.GetProjectList2(project);
                List<ProjectWithProgress> ll2 = new ArrayList<>();//存符合条件项目的信息的那15条
                for (ProjectWithProgress p : l1) {
                    l2 = projectMapper.GetProjectValue(p.getProject_id());
                    int temp = 0;
                    for (Projectvalue pv : l2) {//获取最新进度
                        temp = pv.getProject_progress();
                        temp = max(temp, pv.getProject_progress());
                    }
                    p.setProject_progress(temp);//设置最新进度
                    ll1.add(p);
                }
                if(ll1.size()>=15) {
                    for (int i = 15 * (Integer.parseInt(project.getPage()) - 1); i < 15 * Integer.parseInt(project.getPage()); i++) {
                        if(ll1.size()-1 < i)
                            break;
                        ll2.add(ll1.get(i));
                    }
                    map.put("TotalResult", String.valueOf(ll1.size()));
                    map.put("ProjectList", ll2);
                    return new NewState("200", "该项目信息如下", map);
                }
            }
            map.put("TotalResult", String.valueOf(ll1.size()));
            map.put("ProjectList", ll1);
            return new NewState("200", "该项目信息如下", map);
        }catch (Exception e){
            return new NewState("400", e.toString());
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
        try {
            if(projectMapper.GetProjectname1(project.getProject_name())!=null) {
                return new NewState("400", "项目名称重复");
            } else if(!Objects.equals(employeeMapper.get_type(project.getUser_id()), "管理员") && !Objects.equals(employeeMapper.get_type(project.getUser_id()), "组长")) {
                return new NewState("400", "权限不足");
            } else {
                projectMapper.UpdateProject(project);
                map.put("user_id", project.getUser_id());
                map.put("project_id", projectMapper.GetProject_id(project.getProject_name()));
                return new NewState("200", "项目信息修改成功", map);
            }
        }catch (Exception e){
            return new NewState("401", "必填项未填");
        }
    }

    //关停、激活项目
    @RequestMapping(value = "/projectstatus", method = RequestMethod.POST)
    public NewState startproject(@RequestBody(required = false) Project project) {
        try {

            if(!Objects.equals(employeeMapper.get_type(project.getUser_id()), "管理员") && !Objects.equals(employeeMapper.get_type(project.getUser_id()), "组长")) {
                return new NewState("400", "权限不足");
            } else {
                projectMapper.UpdateProjectstatus(project);
                return new NewState("200", "项目状态修改成功");
            }
        }catch (Exception e){
            return new NewState("400", "出现未知错误");
        }
    }



}

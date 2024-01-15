package com.example.demo.controller;

import com.example.demo.entity.Project;
import com.example.demo.entity.State;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.mapper.UsersMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")//跨域
@RestController
public class ProjectManageController {
    @Resource
    private ProjectMapper projectMapper;//实例化对象并注入
    //新建项目
    @RequestMapping(value = "/Newproject", method = RequestMethod.POST)
    public State newproject(Project project) {
        if (project.getContract_amount()==null)
            return new State("请保证信息输入完整");
        else {
            projectMapper.CreateProject(project);
            return new State(project.getProject_name() + "创建成功", project.getUser_id());
        }
    }
    //项目信息查询
    @RequestMapping(value = "/ProjectSearch", method = RequestMethod.POST)
    public List<Project> projectsearch(Project project) {
        return projectMapper.GetProject(project);
    }

    //项目信息修改
    @RequestMapping(value = "/Changeproject", method = RequestMethod.POST)
    public State ChangeProject(Project project) {
        projectMapper.UpdateProject(project);
        return new State("项目信息修改成功", project.getUser_id());
    }



}

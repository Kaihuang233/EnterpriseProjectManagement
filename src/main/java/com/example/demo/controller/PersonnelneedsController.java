package com.example.demo.controller;

import com.example.demo.entity.Personnelneeds;
import com.example.demo.entity.Project;
import com.example.demo.entity.State;
import com.example.demo.mapper.PersonnelneedsMapper;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.mapper.UsersMapper;
import jakarta.annotation.Resource;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@CrossOrigin(origins = "*")//跨域
@RestController
public class PersonnelneedsController {
    @Resource
    private PersonnelneedsMapper personnelneedsMapper;//实例化对象并注入
    //周报
    @RequestMapping(value = "/Personneeds", method = RequestMethod.POST)
    public State NewPersonnelneeds(@RequestBody Personnelneeds[] personnelneeds) {//新增人员需求
        int i = 0;
        for(Personnelneeds p : personnelneeds) {
            personnelneedsMapper.CreatePerneeds(p);
            System.out.println(p.toString());
            i++;
        }
        System.out.println(Arrays.toString(personnelneeds));
        return new State("共"+i+"位的人员需求已添加");
    }

    public List<Personnelneeds> GetPersonnelneeds(int project_id) {//获取该项目id的人员需求列表
        List<Personnelneeds> list = personnelneedsMapper.getinfo(project_id);
        return list;
    }
}


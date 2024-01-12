package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Personnelarrangement;
import com.example.demo.entity.Personnelneeds;
import com.example.demo.entity.State;
import com.example.demo.mapper.PersonnelarrangementMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")//跨域
@RestController
public class PersonnelArrangeController {
    @Resource
    private PersonnelarrangementMapper personnelarrangementMapper;
    //新增人员安排
    @RequestMapping(value = "/Arrangement", method = RequestMethod.POST)
    public State Arrangement(@RequestBody Personnelarrangement[] personnelarrangements) {
        int i = 0;
        for(Personnelarrangement p : personnelarrangements){
            personnelarrangementMapper.reg(p);
            i++;
        }
        return new State("共"+i+"条安排添加成功");
    }
}

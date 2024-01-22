package ProjectManagement.controller;

import ProjectManagement.entity.Project;
import ProjectManagement.entity.State;
import ProjectManagement.entity.Personnelarrangement;
import ProjectManagement.mapper.PersonnelarrangementMapper;
import ProjectManagement.mapper.ProjectMapper;
import ProjectManagement.service.PersonnelArrangeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@CrossOrigin(origins = "*")//跨域
@RestController
public class PersonnelArrangeController implements PersonnelArrangeService {
    @Resource
    private PersonnelarrangementMapper personnelarrangementMapper;

    //新增人员安排
    @RequestMapping(value = "/newArrangement", method = RequestMethod.POST)
    public State newArrangement(@RequestBody Personnelarrangement[] personnelarrangements) {
        int i = 0;
        for(Personnelarrangement p : personnelarrangements){
            personnelarrangementMapper.reg(p);
            i++;
        }
        return new State("共"+i+"条安排添加成功");
    }

    //获取某项目的人员安排列表
    @RequestMapping(value = "/Arrangement", method = RequestMethod.POST)
    public State Arrangement(@RequestBody(required = false) Personnelarrangement personnelarrangement) {
        return new State("该项目人员安排如下", personnelarrangementMapper.getpersonarrange(personnelarrangement.getProject_id()));
    }
    //返回今年有项目的人员安排对象
    public List<Personnelarrangement> Theyear(Personnelarrangement personnelarrangement){
        return personnelarrangementMapper.Theyear(personnelarrangement);
    }
    //返回今年项目持续一整年的人员安排对象
    public List<Personnelarrangement> TheWholeyear(Personnelarrangement personnelarrangement){
        return personnelarrangementMapper.TheWholeyear(personnelarrangement);
    }
}

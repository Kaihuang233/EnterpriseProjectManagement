package ProjectManagement.controller;

import ProjectManagement.entity.NewState;
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
import java.util.Map;
import java.util.TreeMap;

@CrossOrigin(origins = "*")//跨域
@RestController
public class PersonnelArrangeController implements PersonnelArrangeService {
    @Resource
    private PersonnelarrangementMapper personnelarrangementMapper;

    private Map<String, Object> map;

    //新增人员安排
    @RequestMapping(value = "/newArrangement", method = RequestMethod.POST)
    public NewState newArrangement(@RequestBody Personnelarrangement[] personnelarrangements) {
        map = new TreeMap<>();
        int i = 0;
        for(Personnelarrangement p : personnelarrangements){
            personnelarrangementMapper.reg(p);
            i++;
        }
        return new NewState("200", "共"+i+"条安排添加成功");
    }

    //获取某项目的人员安排列表
    @RequestMapping(value = "/Arrangement", method = RequestMethod.POST)
    public NewState Arrangement(@RequestBody(required = false) Personnelarrangement personnelarrangement) {
        map = new TreeMap<>();
        map.put("employee_id", personnelarrangementMapper.getpersonarrange(personnelarrangement.getProject_id()));
        return new NewState("200", "该项目人员安排如下", map);
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

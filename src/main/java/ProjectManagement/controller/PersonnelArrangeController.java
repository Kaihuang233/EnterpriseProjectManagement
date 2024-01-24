package ProjectManagement.controller;

import ProjectManagement.entity.*;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.PersonnelarrangementMapper;
import ProjectManagement.mapper.ProjectMapper;
import ProjectManagement.service.PersonnelArrangeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")//跨域
@RestController
public class PersonnelArrangeController implements PersonnelArrangeService {
    @Resource
    private PersonnelarrangementMapper personnelarrangementMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    private Map<String, Object> map;

    //新增人员安排
    @RequestMapping(value = "/newArrangement", method = RequestMethod.POST)
    public NewState newArrangement(@RequestBody List<Personnelarrangement> personnelarrangements) {
        map = new TreeMap<>();
        if (!Objects.equals(employeeMapper.get_type(personnelarrangements.get(0).getUser_id()), "管理员") && !Objects.equals(employeeMapper.get_type(personnelarrangements.get(0).getUser_id()), "组长")) {
            return new NewState("400", "权限不足");
        }
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
        try {
            List<Personnelarrangement> l = personnelarrangementMapper.getpersonarrangePlus(personnelarrangement.getProject_id());
            for (int i = 0; i < l.size(); i++) {
                Personnelarrangement p = l.get(i);
                p.setName(employeeMapper.get_nameByemp_id(l.get(i).getEmployee_id()));
                l.set(i, p);
            }
            map.put("Staffing", l);
            return new NewState("200", "该项目人员安排如下", map);
        }catch (Exception e){
            return new NewState("400", "出现未知错误");
        }
    }

    //将某员工从项目中删除
    @RequestMapping(value = "/delEmployee", method = RequestMethod.POST)
    public NewState DelEmployee(@RequestBody(required = false) Employee employee) {
        if (!Objects.equals(employeeMapper.get_type(employee.getUser_id()), "管理员") && !Objects.equals(employeeMapper.get_type(employee.getUser_id()), "组长")) {
            return new NewState("400", "权限不足");
        }
        personnelarrangementMapper.delete(employee);
        return new NewState("200", "员工信息已删除");
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

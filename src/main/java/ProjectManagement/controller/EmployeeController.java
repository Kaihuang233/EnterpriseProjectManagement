package ProjectManagement.controller;


import ProjectManagement.entity.Employee;
import ProjectManagement.entity.Personnelarrangement;
import ProjectManagement.entity.State;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.PersonnelarrangementMapper;
import ProjectManagement.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")//跨域
@RestController
public class EmployeeController implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;//实例化对象并注入
    @Resource
    private PersonnelarrangementMapper personnelarrangementMapper;//实例化对象并注入

    //新增职员
    @RequestMapping(value = "/NewEmployee", method = RequestMethod.POST)
    public State newemployee(Employee employee) {
        if(employee.getName().isEmpty()||employee.getMail().isEmpty()||employee.getTelnum().isEmpty()) {
            return new State("请补全信息");
        }
        else {
            employeeMapper.CreateEmployee(employee);
            return new State("增添人员" + employee.getName() + "成功");
        }
    }

    //获取职员列表
    @RequestMapping(value = "/GetEmployeeList", method = RequestMethod.GET)
    public State getemployee() {
        return new State("员工id列表如下", employeeMapper.getemplist());
    }

    //获得某日期空闲的员工列表
    @RequestMapping(value = "/GetEmpArrange", method = RequestMethod.POST)
    public State getEmpArrange(Personnelarrangement personnelarrangement) {
        return new State("员工id列表如下", personnelarrangementMapper.getperson(personnelarrangement));
    }

    //根据姓名获取员工信息
    @RequestMapping(value = "/GetEmpinfo", method = RequestMethod.POST)
    public State getEmpinfo(Employee employee) {
        if(Objects.equals(employee.getName(), "") || Objects.equals(employee.getPost(), ""))
            return new State("员工信息如下", employeeMapper.getinfo1(employee));
        else
            return new State("员工信息如下", employeeMapper.getinfo2(employee));
    }

}


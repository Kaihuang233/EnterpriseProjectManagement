package com.example.demo.controller;


import com.example.demo.entity.Employee;
import com.example.demo.entity.Personnelarrangement;
import com.example.demo.entity.State;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.PersonnelarrangementMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "*")//跨域
@RestController
public class EmployeeController {
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
    public List<Employee> getemployee() {
        return employeeMapper.getemplist();
    }

    //获得某日期空闲的员工列表
    @RequestMapping(value = "/GetEmpArrange", method = RequestMethod.POST)
    public List<Integer> getEmpArrange(Personnelarrangement personnelarrangement) {
        return personnelarrangementMapper.getperson(personnelarrangement);
    }


}


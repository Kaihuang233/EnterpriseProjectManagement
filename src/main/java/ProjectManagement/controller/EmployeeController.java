package ProjectManagement.controller;


import ProjectManagement.entity.Employee;
import ProjectManagement.entity.NewState;
import ProjectManagement.entity.Personnelarrangement;
import ProjectManagement.entity.State;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.PersonnelarrangementMapper;
import ProjectManagement.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")//跨域
@RestController
public class EmployeeController implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;//实例化对象并注入
    @Resource
    private PersonnelarrangementMapper personnelarrangementMapper;//实例化对象并注入
    private Map<String, Object> map;

    //新增职员
    @RequestMapping(value = "/NewEmployee", method = RequestMethod.POST)
    public NewState newemployee(@RequestBody(required = false) Employee employee) {
        if(employee.getName().isEmpty()||employee.getMail().isEmpty()||employee.getTelnum().isEmpty()) {
            return new NewState("401", "请补全信息");
        }
        else {
            employeeMapper.CreateEmployee(employee);
            return new NewState("增添人员" + employee.getName() + "成功");
        }
    }

    //获取职员列表
    @RequestMapping(value = "/GetEmployeeList", method = RequestMethod.GET)
    public NewState getemployee() {
        map = new TreeMap<>();
        map.put("list", employeeMapper.getemplist());
        return new NewState("200", "员工id列表如下", map);
    }

    //获得某日期空闲的员工列表
    @RequestMapping(value = "/GetEmpArrange", method = RequestMethod.POST)
    public NewState getEmpArrange(@RequestBody(required = false) Personnelarrangement personnelarrangement) {
        personnelarrangement.setEnd_date(Transform.trans(personnelarrangement.getEnd_date_json()));
        map = new TreeMap<>();
        map.put("list", personnelarrangementMapper.getperson(personnelarrangement));
        return new NewState("200", "员工id列表如下", map);
    }

    //根据姓名获取员工信息
    @RequestMapping(value = "/GetEmpinfo", method = RequestMethod.POST)
    public NewState getEmpinfo(@RequestBody(required = false) Employee employee) {
        map = new TreeMap<>();
        try {
            if (Objects.equals(employee.getName(), "") || Objects.equals(employee.getPost(), "")) {
                map.put("list", employeeMapper.getinfo1(employee));
            } else {
                map.put("list", employeeMapper.getinfo2(employee));
            }
            return new NewState("200", "员工信息如下", map);
        }catch (Exception e){
            return new NewState("400", "出现未知错误", map);
        }
    }

    //将某员工设为离职
    @RequestMapping(value = "/delEmployee", method = RequestMethod.POST)
    public NewState DelEmployee(@RequestBody(required = false) Employee employee) {
        employee.setPost("离职");
        employee.setType("离职");
        employee.setSalary(0);
        employeeMapper.setemployee(employee);
        personnelarrangementMapper.delete(employee);
        return new NewState("200", "员工信息已删除");
    }

    //修改某员工信息
    @RequestMapping(value = "/UpdateEmployee", method = RequestMethod.POST)
    public NewState UpdateEmployee(@RequestBody(required = false) Employee employee) {
        employeeMapper.Upemployee(employee);
        return new NewState("200", "员工信息已修改");
    }

}


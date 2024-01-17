package ProjectManagement.service;

import ProjectManagement.entity.Employee;
import ProjectManagement.entity.Personnelarrangement;
import ProjectManagement.entity.State;

import java.util.List;

public interface EmployeeService {
    State getemployee();//新增职员
    State newemployee(Employee employee);//获取职员列表
    State getEmpArrange(Personnelarrangement personnelarrangement); //获得某日期空闲的员工列表
}

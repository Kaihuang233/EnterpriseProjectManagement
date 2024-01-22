package ProjectManagement.service;

import ProjectManagement.entity.Employee;
import ProjectManagement.entity.NewState;
import ProjectManagement.entity.Personnelarrangement;
import ProjectManagement.entity.State;

import java.util.List;

public interface EmployeeService {
    NewState getemployee();//新增职员
    NewState newemployee(Employee employee);//获取职员列表
    NewState getEmpArrange(Personnelarrangement personnelarrangement); //获得某日期空闲的员工列表
}

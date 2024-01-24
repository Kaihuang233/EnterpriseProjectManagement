package ProjectManagement.service;

import ProjectManagement.entity.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeService {
    NewState getemployee();//新增职员

    NewState newemployee(Employee employee);//获取职员列表

    NewState getEmpArrange(Personnelarrangement personnelarrangement); //获得某日期空闲的员工列表

    NewState searchEmployeeList( EmployeePlus employeePlus);//搜索职员列表

    NewState getEmpinfo(Employee employee);//根据姓名获取员工信息

    NewState GetAllpost();//获取职务类型列表

    NewState UpdateEmployee(Employee employee);//修改某员工信息
}

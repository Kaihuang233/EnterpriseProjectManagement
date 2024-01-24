package ProjectManagement.service;

import ProjectManagement.entity.Employee;
import ProjectManagement.entity.NewState;
import ProjectManagement.entity.State;
import ProjectManagement.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    NewState login(User user);//登录
    NewState register(User user);//注册
    NewState BasicInfoModify(Employee employee);//个人信息完善
    NewState ImportantInfoModify(User user);//修改重要信息
    NewState resetpassword(User user);//重设密码并登录
}

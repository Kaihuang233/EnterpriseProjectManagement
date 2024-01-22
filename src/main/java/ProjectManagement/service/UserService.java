package ProjectManagement.service;

import ProjectManagement.entity.Employee;
import ProjectManagement.entity.NewState;
import ProjectManagement.entity.State;
import ProjectManagement.entity.User;

public interface UserService {
    NewState login(User user);
    NewState register(User user);
    NewState BasicInfoModify(Employee employee);
    NewState ImportantInfoModify(User user);
}

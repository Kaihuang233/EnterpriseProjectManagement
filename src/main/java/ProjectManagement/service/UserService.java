package ProjectManagement.service;

import ProjectManagement.entity.Employee;
import ProjectManagement.entity.State;
import ProjectManagement.entity.User;

public interface UserService {
    State login(User user);
    State register(User user, String mail, String code);
    State BasicInfoModify(Employee employee);
    State ImportantInfoModify(User user, String mail, String code);
}

package ProjectManagement.controller;

import ProjectManagement.entity.Employee;
import ProjectManagement.entity.NewState;
import ProjectManagement.entity.State;
import ProjectManagement.entity.User;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.UsersMapper;
import ProjectManagement.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "*")//跨域
@RestController
public class UserController implements UserService {

    @Resource
    private UsersMapper usermapper;//实例化对象并注入
    @Resource
    private EmployeeMapper employeeMapper;//实例化对象并注入

    private Map<String, Object> map;


    //登录
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public NewState login(@RequestBody(required = false)User user) {
        map = new HashMap<>();
        map.put("type", employeeMapper.get_type(usermapper.getuser_id(user.getUser_name())));
        map.put("usr_id", usermapper.getuser_id(user.getUser_name()));
        try{
            if(Objects.equals(usermapper.check(user.getUser_name()), user.getPassword())){//确认密码
                return new NewState("200", "登录成功", map);
        }else{
                return new NewState("412", "密码错误");
        }
    }catch (Exception e){
            return new NewState("400", e.toString());
        }
    }

    //注册
    @RequestMapping(value = "/SignUp", method = RequestMethod.POST)
    public NewState register(@RequestBody(required = false)User user) {
        map = new HashMap<>();
        try{
        if(user.getUser_name() == null){
            return new NewState("401", "请输入用户名");
        } else if(user.getTelnum() == null && user.getPassword() == null){//密码电话都空
            return new NewState("401", "请输入电话和密码");
        } else if (user.getTelnum() == null) {//电话空
            return new NewState("401", "请输入电话");
        } else if(user.getPassword() == null){//密码空
            return new NewState("401","请输入密码");
        }else if(usermapper.checktelnum(user.getTelnum())!=null){//检查手机号有没有注册过
            return new NewState("409", "注册失败，注册使用的手机号已存在");
        }else if(!Objects.equals(employeeMapper.checkcode(user.getMail()), user.getCode())){
            return new NewState("401","验证码错误，请重新输入");
        }else{
            usermapper.reg(user);
            employeeMapper.Set_user(user.getTelnum(), usermapper.getuser_idByphone(user.getTelnum()));

            map.put("type", employeeMapper.get_type(usermapper.getuser_idByphone(user.getTelnum())));
            map.put("user_id", usermapper.getuser_idByphone(user.getTelnum()));
            return new NewState("401", "注册成功！", map);
        }}catch (Exception e){
            return new NewState("400", e.toString());
        }
    }

    //个人信息完善
    @RequestMapping(value = "/InformationCompletion1", method = RequestMethod.POST)
    public NewState BasicInfoModify(@RequestBody(required = false)Employee employee) {
        map = new HashMap<>();
        try {
            if (employee.getSex() == null || employee.getAge() == 0 || employee.getCity() == null)
                return new NewState("401", "请补全信息！");
            else if(!Objects.equals(employeeMapper.get_name(employee.getUser_id()), employee.getName()))
                return new NewState("401", "用户id与职员不对应");
            employeeMapper.updatebasic(employee);

            map.put("name", employeeMapper.get_name(employee.getUser_id()));
            map.put("user_id", employee.getUser_id());
            return new NewState("200", employeeMapper.get_name(employee.getUser_id())+"信息修改成功！", map);
        }catch (Exception e){
            return new NewState("400", "出现未知原因错误");
        }
    }
    //修改重要信息
    @RequestMapping(value = "/InformationCompletion2", method = RequestMethod.POST)
    public NewState ImportantInfoModify(@RequestBody(required = false)User user) {
        map = new HashMap<>();
        try {
            if (Objects.equals(user.getCode(), employeeMapper.checkcode(user.getMail()))) {
                if (user.getTelnum() == null || user.getPassword() == null || user.getMail() == null)
                    return new NewState("401", "请补全信息！");
                usermapper.updateimportant(user);
                employeeMapper.Set_tel(user.getTelnum(), user.getUser_id());
                employeeMapper.Set_mail(user.getMail(), user.getUser_id());

                map.put("user_id", user.getUser_id());
                return new NewState("200","信息修改成功！", map);
            } else
                return new NewState("401", "验证码错误！");
        }catch (Exception e){
            return new NewState("400", "未知原因错误");
        }
    }

}

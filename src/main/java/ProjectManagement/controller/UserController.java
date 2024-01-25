package ProjectManagement.controller;

import ProjectManagement.entity.Employee;
import ProjectManagement.entity.NewState;
import ProjectManagement.entity.State;
import ProjectManagement.entity.User;
import ProjectManagement.mapper.EmailverificationMapper;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.UsersMapper;
import ProjectManagement.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

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
        map.put("user_id", usermapper.getuser_id(user.getUser_name()));
        map.put("user_name", user.getUser_name());
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

    //重设密码并登录
    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public NewState resetpassword(@RequestBody(required = false)User user) {
        map = new TreeMap<>();
        try{
            if(user.getUser_name() == null){
                return new NewState("401", "请输入用户名");
            }else if(user.getPassword() == null){//密码空
                return new NewState("401","请输入密码");
            }else if(user.getUser_name().length() < 3 || user.getUser_name().length() > 20){//用户名位数
                return new NewState("401","用户名位数应在3-20位之间");
            }else if(user.getPassword().length() < 6 || user.getPassword().length() > 20){//密码位数
                return new NewState("401","密码位数应在6-20位之间");
            }else if((user.getPassword()).contains(" ")){//密码包含空格
                return new NewState("401","密码含空格");
            }else if((user.getUser_name()).contains(" ")){//用户名包含空格
                return new NewState("401","用户名含空格");
            } else if (!Objects.equals(user.getCode(), employeeMapper.checkcode1(usermapper.getuser_id(user.getUser_name())))|| Objects.equals(user.getCode(), "")) {//验证码不正确
                return new NewState("401","验证码错误");
            }else{
                usermapper.updatepassword(usermapper.getuser_id(user.getUser_name()), user.getPassword());
                employeeMapper.updatecode(employeeMapper.get_mail(usermapper.getuser_id(user.getUser_name())), "");
                map.put("user_name", user.getUser_name());
                return new NewState("400","密码修改成功，登录成功");
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
        }else if (user.getTelnum().length() != 11) {//电话位数不正确
            return new NewState("401", "电话号码位数不正确");
        } else if(user.getPassword() == null){//密码空
            return new NewState("401","请输入密码");
        }else if(user.getUser_name().length() < 3 || user.getUser_name().length() > 20){//用户名位数
            return new NewState("401","用户名位数应在3-20位之间");
        }else if(user.getPassword().length() < 6 || user.getPassword().length() > 20){//密码位数
            return new NewState("401","密码位数应在6-20位之间");
        }else if((user.getPassword()).contains(" ")){//密码包含空格
            return new NewState("401","密码含空格");
        }else if((user.getUser_name()).contains(" ")){//用户名包含空格
            return new NewState("401","用户名含空格");
        }else if(usermapper.checktelnum(user.getTelnum())!=null){//检查手机号有没有注册过
            return new NewState("409", "注册失败，注册使用的手机号已存在");
        }else if(usermapper.checkmail(user.getMail())!=null){
            return new NewState("409", "注册失败，注册使用的邮箱已存在");
        }else if(usermapper.checkname(user.getUser_name())!=null){
            return new NewState("409", "注册失败，注册使用的用户名已存在");
        }else if(!Objects.equals(employeeMapper.checkcode(user.getMail()), user.getCode())|| Objects.equals(user.getCode(), "")){
            return new NewState("401","验证码错误，请重新输入");
        }else{
            usermapper.reg(user);
            employeeMapper.Set_user(user.getTelnum(), usermapper.getuser_idByphone(user.getTelnum()));

            map.put("type", employeeMapper.get_type(usermapper.getuser_idByphone(user.getTelnum())));
            map.put("user_id", usermapper.getuser_idByphone(user.getTelnum()));
            map.put("user_name", user.getUser_name());
            employeeMapper.updatecode(user.getMail(), "");//将验证码设为空
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

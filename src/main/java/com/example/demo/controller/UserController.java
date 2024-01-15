package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.EmailverificationMapper;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.UsersMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")//跨域
@RestController
public class UserController {

    @Resource
    private UsersMapper usermapper;//实例化对象并注入
    @Resource
    private EmployeeMapper employeeMapper;//实例化对象并注入

    //登录
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public State login(User user) {
        if(user.getUser_name() == null && user.getPassword() == null){
            return new State("请输入用户名和密码");
        } else if(user.getUser_name() == null){
            return new State("请输入用户名");
        } else if (user.getPassword() == null) {//电话空
            return new State("请输入密码");
        } else if(!Objects.equals(usermapper.check(user.getUser_name()), user.getPassword())){//密码空
            return new State("用户名或密码错误,请重新输入");
        }else{
            return new State("登录成功！", usermapper.getuser_id(user.getTelnum()), employeeMapper.get_type(usermapper.getuser_id(user.getTelnum())));
        }
    }

    //注册
    @RequestMapping(value = "/SignUp", method = RequestMethod.POST)
    public State register(User user, String mail, String code) {
        if(user.getUser_name() == null){
            return new State("请输入用户名");
        } else if(user.getTelnum() == null && user.getPassword() == null){//密码电话都空
            return new State("请输入电话和密码");
        } else if (user.getTelnum() == null) {//电话空
            return new State("请输入电话");
        } else if(user.getPassword() == null){//密码空
            return new State("请输入密码");
        }else if(usermapper.checktelnum(user.getTelnum())!=null){//检查手机号有没有注册过
            return new State("注册失败，注册使用的手机号已存在");
        }else if(!Objects.equals(employeeMapper.checkcode(mail), code)){
            return new State("验证码错误，请重新输入");
        }else{
            usermapper.reg(user);
            employeeMapper.Set_user(user.getTelnum(), usermapper.getuser_id(user.getTelnum()));
            return new State("注册成功！", usermapper.getuser_id(user.getTelnum()), employeeMapper.get_type(user.getUser_id()));
        }
    }

    //个人信息完善
    @RequestMapping(value = "/InformationCompletion1", method = RequestMethod.POST)
    public State BasicInfoModify(Employee employee) {
        try {
            if (employee.getSex() == null || employee.getAge() == 0 || employee.getCity() == null)
                return new State("请补全信息！");
            else if(!Objects.equals(employeeMapper.get_name(employee.getUser_id()), employee.getName()))
                return new State("用户id与职员不对应");
        }catch (Exception e){
            return new State("请补全信息！");
        }
        employeeMapper.updatebasic(employee);
        return new State(employeeMapper.get_name(employee.getUser_id())+"信息修改成功！", employee.getUser_id());
    }

    @RequestMapping(value = "/InformationCompletion2", method = RequestMethod.POST)
    public State ImportantInfoModify(User user, String mail, String code) {
        if(Objects.equals(code, employeeMapper.checkcode(mail))) {
            if(user.getTelnum() == null || user.getPassword() == null || mail == null)
                return new State("请补全信息！");
            usermapper.updateimportant(user);
            employeeMapper.Set_tel(user.getTelnum(), user.getUser_id());
            employeeMapper.Set_mail(mail, user.getUser_id());
            return new State("信息修改成功！",user.getUser_id());
        }
        else
            return new State("验证码错误！");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public State shi(){
        System.out.println(employeeMapper.getinfo("周济锴"));
        return new State("成功");
    }
}

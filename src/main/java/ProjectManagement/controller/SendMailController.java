package ProjectManagement.controller;


import ProjectManagement.entity.*;
import ProjectManagement.mapper.EmailverificationMapper;
import ProjectManagement.mapper.EmployeeMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import ProjectManagement.mapper.UsersMapper;

@RestController
public class SendMailController {
    @Autowired
    private SendMail sendMailService;

    @Autowired
    private TemplateEngine templateEngine;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private EmployeeMapper employeeMapper;

    private Map<String, Object> map;

    @RequestMapping(value = "/sendmail",method = RequestMethod.POST)
    public NewState sendOne(@RequestBody(required = false) User user) {
        if(user.getMail() == null)
            return new NewState("401", "请输入邮箱");
        String code = verificationCode();
        sendMailService.sendTxtMail1(user.getMail(),"您好，您的验证码为：",code+" \n验证码在五分钟内有效，请尽快进行验证");
        if(employeeMapper.check(user.getMail())==null)
            return new NewState("401", "邮箱不存在");
        else{
            employeeMapper.updatecode(user.getMail(),code);//更新验证码
            return new NewState("200", "发送成功");
        }
    }
    //验证验证码
    @RequestMapping(value = "/vertifymail",method = RequestMethod.POST)
    public NewState vertifymail(@RequestBody(required = false)Employee employee) {
        if(Objects.equals(employeeMapper.checkcode(employee.getMail()), employee.getCode())){
            if(!Objects.equals(employee.getCode(), "")) {
                employeeMapper.updatecode(employee.getMail(), "");//将验证码设为空
                return new NewState("200", "验证通过");
            }
        }
        return new NewState("401", "验证失败");

    }

    //找回密码
    @RequestMapping(value = "/findpassword", method = RequestMethod.POST)
    public NewState findpassword(@RequestBody(required = false)User user) {
        map = new HashMap<>();
        try {
            if(Objects.equals(usersMapper.findmail(usersMapper.getuser_id(user.getUser_name())), user.getMail())) {//邮箱对应该用户
                String code = verificationCode();
                sendMailService.sendTxtMail1(user.getMail(),"您好，您的验证码为：",code+" \n验证码在五分钟内有效，请尽快进行验证");//发送验证码
                employeeMapper.updatecode(user.getMail(),code);//更新验证码
                return new NewState("200", "验证码发送成功");
            }else{
                return new NewState("401", "关联邮箱错误");
            }
        }catch (Exception e){
            return new NewState("400", "出现未知原因错误");
        }
    }

    public static String verificationCode() {
        //生成六位随机正整数
        Random random = new Random();
        String verificationCode = String.valueOf(random.nextInt(9) + 1);
        for (int i = 0; i < 5; i++) {
            verificationCode += random.nextInt(10);
        }
        return verificationCode;
    }

}


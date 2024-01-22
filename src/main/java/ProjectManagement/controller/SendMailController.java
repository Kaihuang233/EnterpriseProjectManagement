package ProjectManagement.controller;


import ProjectManagement.entity.SendMail;
import ProjectManagement.entity.User;
import ProjectManagement.mapper.EmployeeMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ProjectManagement.entity.State;
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

    @RequestMapping(value = "/sendmail",method = RequestMethod.POST)
    public State sendOne(@RequestBody(required = false) User user) {
        if(user.getMail() == null)
            return new State("请输入邮箱");
        String code = verificationCode();
        sendMailService.sendTxtMail1(user.getMail(),"您好，您的验证码为：",code+" \n验证码在五分钟内有效，请尽快进行验证");
        if(employeeMapper.check(user.getMail())==null)
            return new State("邮箱不存在");
        else{
            employeeMapper.updatecode(user.getMail(),code);//更新验证码
            return new State("发送成功");
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


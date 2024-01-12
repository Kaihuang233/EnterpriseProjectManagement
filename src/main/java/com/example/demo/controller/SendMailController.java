package com.example.demo.controller;


import com.example.demo.entity.Emailverification;
import com.example.demo.entity.SendMail;
import com.example.demo.mapper.EmailverificationMapper;
import com.example.demo.mapper.EmployeeMapper;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Random;
import com.example.demo.entity.State;
import com.example.demo.mapper.UsersMapper;

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
    public State sendOne(String mail) {
        if(mail == null)
            return new State("请输入邮箱");
        String code = verificationCode();
        sendMailService.sendTxtMail1(mail,"您好，您的验证码为：",code+" \n验证码在五分钟内有效，请尽快进行验证");
        if(employeeMapper.check(mail)==null)
            return new State("邮箱不存在");
        else{
            employeeMapper.updatecode(mail,code);//更新验证码
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


package ProjectManagement.controller;

import ProjectManagement.entity.Messageborad;
import ProjectManagement.entity.NewState;
import ProjectManagement.mapper.MessageBoradMapper;
import ProjectManagement.entity.State;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.service.MessageboradService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")//跨域
@RestController
public class MessageboradController implements MessageboradService {
    @Resource
    private MessageBoradMapper messageBoradMapper;//实例化对象并注入

    @Resource
    private EmployeeMapper employeeMapper;//实例化对象并注入
    //发表留言
    @RequestMapping(value = "/newMessage", method = RequestMethod.POST)
    public State NewMessageborad(@RequestBody(required = false) Messageborad messageborad) {
        messageborad.setType(employeeMapper.get_type(messageborad.getUser_id()));
        messageBoradMapper.CreateMessage(messageborad);
        return new State("留言已发表", messageborad.getUser_id());
    }

}

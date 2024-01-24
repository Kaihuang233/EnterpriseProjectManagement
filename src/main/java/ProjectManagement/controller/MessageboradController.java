package ProjectManagement.controller;

import ProjectManagement.entity.Messageborad;
import ProjectManagement.entity.NewState;
import ProjectManagement.mapper.MessageBoradMapper;
import ProjectManagement.entity.State;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.UsersMapper;
import ProjectManagement.service.MessageboradService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")//跨域
@RestController
public class MessageboradController implements MessageboradService {
    @Resource
    private MessageBoradMapper messageBoradMapper;//实例化对象并注入

    @Resource
    private EmployeeMapper employeeMapper;//实例化对象并注入

    @Resource
    private UsersMapper usersMapper;//实例化对象并注入

    private Map<String, Object> map;
    //发表留言
    @RequestMapping(value = "/newMessage", method = RequestMethod.POST)
    public NewState NewMessageborad(@RequestBody(required = false) Messageborad messageborad) {
        map = new TreeMap<>();
        messageborad.setType(employeeMapper.get_type(messageborad.getUser_id()));
        messageBoradMapper.CreateMessage(messageborad);

        map.put("user_id", messageborad.getUser_id());
        return new NewState("200", "留言已发表", map);
    }

    //获取某项目留言
    @RequestMapping(value = "/GetMessageborad", method = RequestMethod.POST)
    public NewState GetMessageborad(@RequestBody(required = false) Messageborad messageborad) {
        map = new TreeMap<>();
        List<Messageborad> l = messageBoradMapper.getMessage(messageborad.getProject_id());
        for(int i = 0; i < l.size(); i++){
            Messageborad e = l.get(i);
            e.setUser_name(usersMapper.getuser_name(l.get(i).getUser_id()));
            l.set(i, e);
        }
        map.put("MessageBoard", l);
        return new NewState("200", "留言信息如下", map);
    }

}

package ProjectManagement.controller;

import ProjectManagement.mapper.PersonnelneedsMapper;
import ProjectManagement.entity.Personnelneeds;
import ProjectManagement.entity.State;
import ProjectManagement.service.PersonnelneedsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@CrossOrigin(origins = "*")//跨域
@RestController
public class PersonnelneedsController implements PersonnelneedsService {
    @Resource
    private PersonnelneedsMapper personnelneedsMapper;//实例化对象并注入

    //新建人员需求
    @RequestMapping(value = "/Personneeds", method = RequestMethod.POST)
    public State NewPersonnelneeds(@RequestBody Personnelneeds[] personnelneeds) {
        int i = 0;
        for(Personnelneeds p : personnelneeds) {
            personnelneedsMapper.CreatePerneeds(p);
            System.out.println(p.toString());
            i++;
        }
        System.out.println(Arrays.toString(personnelneeds));
        return new State("共"+i+"位的人员需求已添加");
    }
    //获取该项目id的人员需求列表
    @RequestMapping(value = "/GetPersonneeds", method = RequestMethod.POST)
    public State GetPersonnelneeds(int project_id) {
        return new State("该项目的人员需求列表如下", personnelneedsMapper.getinfo(project_id));
    }
}


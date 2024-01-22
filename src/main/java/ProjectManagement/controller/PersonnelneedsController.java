package ProjectManagement.controller;

import ProjectManagement.entity.NewState;
import ProjectManagement.entity.Project;
import ProjectManagement.mapper.PersonnelneedsMapper;
import ProjectManagement.entity.Personnelneeds;
import ProjectManagement.entity.State;
import ProjectManagement.service.PersonnelneedsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@CrossOrigin(origins = "*")//跨域
@RestController
public class PersonnelneedsController implements PersonnelneedsService {
    @Resource
    private PersonnelneedsMapper personnelneedsMapper;//实例化对象并注入

    private Map<String, Object> map;

    //新建人员需求
    @RequestMapping(value = "/Personneeds", method = RequestMethod.POST)
    public NewState NewPersonnelneeds(@RequestBody Personnelneeds[] personnelneeds) {
        try {
            int i = 0;
            for (Personnelneeds p : personnelneeds) {
                personnelneedsMapper.CreatePerneeds(p);
                System.out.println(p.toString());
                i++;
            }
            System.out.println(Arrays.toString(personnelneeds));
            return new NewState("200", "共" + i + "位的人员需求已添加");
        }catch (Exception e){
            return new NewState("400", "发生未知错误");
        }
    }
    //获取该项目id的人员需求列表
    @RequestMapping(value = "/GetPersonneeds", method = RequestMethod.POST)
    public NewState GetPersonnelneeds(@RequestBody(required = false) Project project) {
        map = new TreeMap<>();

        map.put("Personnelneeds", personnelneedsMapper.getinfo(project.getProject_id()));
        return new NewState("200", "该项目的人员需求列表如下", map);
    }
}


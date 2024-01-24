package ProjectManagement.controller;

import ProjectManagement.entity.*;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.PersonnelneedsMapper;
import ProjectManagement.service.PersonnelneedsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin(origins = "*")//跨域
@RestController
public class PersonnelneedsController implements PersonnelneedsService {
    @Resource
    private PersonnelneedsMapper personnelneedsMapper;//实例化对象并注入

    @Resource
    private EmployeeMapper employeeMapper;//实例化对象并注入

    private Map<String, Object> map;

    //新建人员需求
    @RequestMapping(value = "/Personneeds", method = RequestMethod.POST)
    public NewState NewPersonnelneeds(@RequestBody PersonnelRequirements personnelRequirements) {
        try {
            int i = 0;
            for (Personnelneeds p : personnelRequirements.getPersonnelneeds()) {
                personnelneedsMapper.CreatePerneeds(p);
                i++;
            }

            return new NewState("200", "共" + i + "位的人员需求已添加");
        } catch (Exception e) {
            System.out.println(e);
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

    //修改人员需求信息
    @RequestMapping(value = "/changeneeds", method = RequestMethod.POST)
    public NewState changeneeds(@RequestBody PersonnelRequirements personnelRequirements) {
        map = new TreeMap<>();
        try {
            if (!Objects.equals(employeeMapper.get_type(personnelRequirements.getUser_id()), "管理员") && !Objects.equals(employeeMapper.get_type(personnelRequirements.getUser_id()), "组长")) {
                return new NewState("400", "权限不足");
            } else {
                personnelneedsMapper.delete(personnelRequirements.getPersonnelneeds().get(0).getProject_id());

                for (Personnelneeds p : personnelRequirements.getPersonnelneeds()) {
                    personnelneedsMapper.CreatePerneeds(p);
                }
                return new NewState("200", "修改成功");
            }
        } catch (Exception e) {
            System.out.println(e);
            return new NewState("400", "出现未知错误");
        }
    }
}



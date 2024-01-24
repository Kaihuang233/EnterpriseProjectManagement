package ProjectManagement.controller;


import ProjectManagement.entity.*;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.PersonnelarrangementMapper;
import ProjectManagement.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.lang.Math.max;

@CrossOrigin(origins = "*")//跨域
@RestController
public class EmployeeController implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;//实例化对象并注入
    @Resource
    private PersonnelarrangementMapper personnelarrangementMapper;//实例化对象并注入
    private Map<String, Object> map;

    //新增职员
    @RequestMapping(value = "/NewEmployee", method = RequestMethod.POST)
    public NewState newemployee(@RequestBody(required = false) Employee employee) {
        if(employee.getName().isEmpty()||employee.getMail().isEmpty()||employee.getTelnum().isEmpty()) {
            return new NewState("401", "请补全信息");
        }
        else {
            employeeMapper.CreateEmployee(employee);
            return new NewState("增添人员" + employee.getName() + "成功");
        }
    }

    //获取职员列表
    @RequestMapping(value = "/GetEmployeeList", method = RequestMethod.GET)
    public NewState getemployee(@RequestBody(required = false) ) {
        map = new TreeMap<>();

        List<String> l = new LinkedList<>();
        l.add("架构师");
        l.add("开发经理");
        l.add("开发主管");
        l.add("前端开发");
        l.add("后端开发");
        l.add("数据库人员");
        l.add("测试工程师");
        List<EmployeeList> employeeLists = new ArrayList<>();
        EmployeeList employeeList;
        for(String s : l){
            employeeList = new EmployeeList();
            employeeList.setPost(s);
            employeeList.setEmployees(employeeMapper.getemplist());
            employeeLists.add(employeeList);
        }
        map.put("EmployeeList", employeeLists);
        return new NewState("200", "员工列表如下", map);
    }

//    //搜索职员列表
//    @RequestMapping(value = "/searchEmployeeList", method = RequestMethod.GET)
//    public NewState searchEmployeeList(EmployeePlus employeePlus) {
//        map = new TreeMap<>();
//
//        try {
//            List<EmployeePlus> l1;
//            List<EmployeePlus> ll1 = new ArrayList<>();//存符合条件项目的信息
//            List<Integer> l2;
//            if (Objects.equals(employeePlus.getName(), "")) {//为空时搜索所有
//                l1 = employeeMapper.getemplistPlus();
//                ll1 = l1;
//                }
//            else{
//                Personnelarrangement personnelarrangement = new Personnelarrangement();
//                personnelarrangement.setEnd_date(employeePlus.getEntry_date());
//                l1 = employeeMapper.getemplistPlus1(employeePlus);
//                l2 = personnelarrangementMapper.getperson(personnelarrangement);
//                if(ll1.size()>=15) {
//                    for (int i = 15 * (Integer.parseInt(project.getPage()) - 1); i < 15 * Integer.parseInt(project.getPage()); i++) {
//                        if(ll1.size()-1 < i)
//                            break;
//                        ll2.add(ll1.get(i));
//                    }
//                    map.put("TotalResult", String.valueOf(ll1.size()));
//                    map.put("ProjectList", ll2);
//                    return new NewState("200", "该项目信息如下", map);
//                }
//            }
//            map.put("TotalResult", String.valueOf(ll1.size()));
//            map.put("ProjectList", ll1);
//            return new NewState("200", "该项目信息如下", map);
//        }catch (Exception e){
//            return new NewState("400", e.toString());
//        }
//    }

    //获得某日期空闲的员工列表
    @RequestMapping(value = "/GetEmpArrange", method = RequestMethod.POST)
    public NewState getEmpArrange(@RequestBody(required = false) Personnelarrangement personnelarrangement) {
        personnelarrangement.setEnd_date(Transform.trans(personnelarrangement.getEnd_date_json()));
        map = new TreeMap<>();
        map.put("list", personnelarrangementMapper.getperson(personnelarrangement));
        return new NewState("200", "员工id列表如下", map);
    }

    //根据姓名获取员工信息
    @RequestMapping(value = "/GetEmpinfo", method = RequestMethod.POST)
    public NewState getEmpinfo(@RequestBody(required = false) Employee employee) {
        map = new TreeMap<>();
        try {
            if (Objects.equals(employee.getName(), "") || Objects.equals(employee.getPost(), "")) {
                map.put("list", employeeMapper.getinfo1(employee));
            } else {
                map.put("list", employeeMapper.getinfo2(employee));
            }
            return new NewState("200", "员工信息如下", map);
        }catch (Exception e){
            return new NewState("400", "出现未知错误", map);
        }
    }

    //获取职务类型列表
    @RequestMapping(value = "/GetAllpost", method = RequestMethod.POST)
    public NewState GetAllpost() {
        map = new TreeMap<>();
        try {
            List<String> l = new LinkedList<>();
            l.add("架构师");
            l.add("开发经理");
            l.add("开发主管");
            l.add("前端开发");
            l.add("后端开发");
            l.add("数据库人员");
            l.add("测试工程师");
            map.put("PostList", l);
            return new NewState("200", "职务类型如下", map);
        }catch (Exception e){
            return new NewState("400", "出现未知错误", map);
        }
    }


    //修改某员工信息
    @RequestMapping(value = "/UpdateEmployee", method = RequestMethod.POST)
    public NewState UpdateEmployee(@RequestBody(required = false) Employee employee) {
        employeeMapper.Upemployee(employee);
        return new NewState("200", "员工信息已修改");
    }

}


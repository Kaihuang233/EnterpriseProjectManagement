package ProjectManagement.controller;

import ProjectManagement.entity.*;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.PersonnelarrangementMapper;
import ProjectManagement.mapper.ProjectMapper;
import ProjectManagement.mapper.TimingMapper;
import ProjectManagement.service.TimingService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import jakarta.annotation.Resource;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*")//跨域
@RestController
public class TimingController implements TimingService {
    @Resource
    private TimingMapper timingMapper;

    @Resource
    private PersonnelarrangementMapper personnelarrangementMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private ProjectMapper projectMapper;


    //返回从当前日期有安排的员工号及其项目日期安排
    @RequestMapping(value = "/gettiming",method = RequestMethod.POST)
    public NewState getTime() {
        try {
            return new NewState("200", "当前日期有安排的员工号及其项目日期安排如下", JSONArray.parseArray(JSON.toJSONString(timingMapper.GetTiming(new Date(System.currentTimeMillis())))));
        }catch (Exception e){
            return new NewState("400", "出现未知错误");
        }
    }


    //获取时序图
    @RequestMapping(value = "/getmap",method = RequestMethod.POST)
    public NewState getmap() {
        List<StaffSchedule> staffSchedules = new LinkedList<>();
        StaffSchedule tempSchedule;
        List<StaffProject> staffProject;
        StaffProject tempProject;
        List<ProjectList> projectList;
        ProjectList tempProjectList;


        Map<String, Integer> dates = getdates();//用于储存从今往后的三个月及其对应的天数

        Map<Integer, String> employee = new TreeMap<>();//用于存储公司所有的职员id及其姓名
        List<Employee> e = employeeMapper.getemplist();
        for(Employee em : e){
            employee.put(em.getEmployee_id(), em.getName());
        }

        List<Personnelarrangement> perarrange;

        Calendar c = Calendar.getInstance();

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");

        System.out.println("从今往后的三个月为："+dates);
        for (Map.Entry<Integer, String> entry1 : employee.entrySet()) {//遍历员工
            perarrange = personnelarrangementMapper.TheDates(new Personnelarrangement(entry1.getKey(), new java.sql.Date(c.getTime().getTime())));//获取某员工从该日期往后的项目安排
            tempSchedule = new StaffSchedule();
            tempSchedule.setStaffName(entry1.getValue());
            System.out.println(entry1.getValue());
            staffProject = new LinkedList<>();
            for(Map.Entry<String, Integer> entry2 : dates.entrySet()){//遍历从今往后三个月
                tempProject = new StaffProject();
                tempProject.setMonth(entry2.getKey());
                tempProject.setDays(entry2.getValue());

                projectList = new LinkedList<>();
                for(Personnelarrangement p : perarrange){//遍历ProjectList

                    tempProjectList = new ProjectList();
                    tempProjectList.setProject_name(projectMapper.GetProjectname(p.getProject_id()));

                    char[] arr = entry2.getKey().toCharArray();
                    Calendar cal = Calendar.getInstance();//设为月末
                    cal.set(Calendar.MONTH, arr[arr.length - 1] - '0' - 1);
                    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                    Calendar t = Calendar.getInstance();
                    t.setTime(p.getEnd_date());
                    if (cal.before(t)) {//如果项目结束日期在该月末之后
                        if (Objects.equals(entry2.getKey(), ft.format(new Date(Calendar.getInstance().getTime().getTime())))) {//遍历当前月时
                            tempProjectList.DayList(getdays(new Date(Calendar.getInstance().getTime().getTime()), new Date(cal.getTime().getTime())));
                        } else {
                            tempProjectList.DayList(getMonthalldays(entry2.getValue()));
                        }
                    } else {//如果项目结束日期在该月末之前
                        if (Objects.equals(entry2.getKey(), ft.format(new Date(Calendar.getInstance().getTime().getTime())))) {//遍历当前月时
                            tempProjectList.DayList(getdays(new Date(Calendar.getInstance().getTime().getTime()), p.getEnd_date()));
                        } else {
                            cal.set(Calendar.DAY_OF_MONTH, 1);//设为该月1号
                            tempProjectList.DayList(getdays(new Date(cal.getInstance().getTime().getTime()), p.getEnd_date()));
                        }
                    }

                    projectList.add(tempProjectList);

                }

                tempProject.setProjectList(projectList);
                staffProject.add(tempProject);
            }
            tempSchedule.setStaffProject(staffProject);
            staffSchedules.add(tempSchedule);
        }
        System.out.println(staffSchedules);
        return new NewState("200", "时序图如下", JSONArray.parseArray(JSON.toJSONString(staffSchedules)));
    }
    //获取从今往后的六个月及其对应的天数
    public Map<String, Integer> getdates(){
        Map<String, Integer> dates = new TreeMap<>();//用于储存从今往后的三个月及其对应的天数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        for(int i = 0; i < 3; i++){
            cal.set(Calendar.DAY_OF_MONTH, 1);//把日期设置为当月第一天
            cal.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
            dates.put(sdf.format(cal.getTime()), cal.get(Calendar.DATE));
            cal.add(Calendar.MONTH, 1);
        }
        return dates;
    }

    public List<String> getMonthalldays(int days){//获取某月的所有天数
        List<String> l = new LinkedList<>();
        for(int i = 1; i <= days; i++){
            l.add(String.valueOf(i));
        }
        return l;
    }

    public List<String> getdays(Date date1, Date date2){//获取某月两个日期间的天数
        List<String> l = new LinkedList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int day1 = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(date2);
        int day2 = cal.get(Calendar.DAY_OF_MONTH);

        for(int i = day1; i <= day2; i++){
            l.add(String.valueOf(i));
        }
        return l;
    }
}

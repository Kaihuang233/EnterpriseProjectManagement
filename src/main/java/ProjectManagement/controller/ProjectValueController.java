package ProjectManagement.controller;

import ProjectManagement.entity.State;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.PersonnelarrangementMapper;
import ProjectManagement.mapper.ProjectMapper;
import ProjectManagement.mapper.ProjectValueMapper;
import ProjectManagement.entity.Projectvalue;
import ProjectManagement.service.ProjectValueService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.*;

@CrossOrigin(origins = "*")//跨域
@RestController
public class ProjectValueController implements ProjectValueService {

    @Resource
    private ProjectValueMapper projectValueMapper;//实例化对象并注入
    @Resource
    private ProjectMapper projectMapper;//实例化对象并注入
    @Resource
    private EmployeeMapper employeeMapper;//实例化对象并注入
    @Resource
    private PersonnelarrangementMapper personnelarrangementMapper;//实例化对象并注入
    //计算项目支撑产值
    public double ComputeProject(int amount, int progress, int current_progress){
        return amount*(current_progress-progress)*0.3;
    }
    //计算某人员各月的支撑产值
    public Map<Integer,Double> ComputePerson(int salary, Date start_date, Date end_date){
        Map<Integer,Double> map=new HashMap<>();

        Calendar start = Calendar.getInstance();
        start.setTime(end_date);
        Calendar end = Calendar.getInstance();
        end.setTime(end_date);

        //计算项目起始月的薪资
        map.put(start.get(Calendar.MONTH) + 1, getsalary(salary, start, getMonthLastday(start)));
        //计算项目中完整月的薪资
        start.add(Calendar.MONTH, +1);//月份加一
        while(end.after(getMonthLastday(start))) {
            start.set(Calendar.DAY_OF_MONTH, 1);//设为1号
            map.put(start.get(Calendar.MONTH) + 1, getsalary(salary, start, getMonthLastday(start)));
            start.add(Calendar.MONTH, +1);//月份加一
        }

        //计算项目截止月的薪资
        start.set(Calendar.DAY_OF_MONTH, 1);//设为1号
        map.put(start.get(Calendar.MONTH) + 1, getsalary(salary, start, end));
        return map;
    }
    //获取该月的最后一天的日期
    public Calendar getMonthLastday(Calendar date){
        //月份+1，天设置为0。下个月第0天，就是这个月最后一天
        date.add(Calendar.MONTH, 1);
        date.set(Calendar.DAY_OF_MONTH, 0);
        return date;
    }
    //计算该时间段的薪资
    public double getsalary(int salary, Calendar date1, Calendar date2){
        Long starTime = date1.getTime().getTime();
        Long endTime = date2.getTime().getTime();
        Long days = ((endTime - starTime) / 24 / 60 / 60 / 1000);//除以一天的毫秒数
        return ((double) days / 30 * salary);
    }

    //提交项目进度
    @RequestMapping(value = "/Newprogress", method = RequestMethod.POST)
    public State newprogress(Projectvalue projectvalue) {
        if(projectValueMapper.existProjectvalue(projectvalue)==null){//第一次提交项目进度时会额外提交一个进度为0提交时间为项目起始日期的项目进度
            System.out.println(projectValueMapper.GetProjectvalue(projectvalue));
            Projectvalue p = new Projectvalue();
            p.setProject_id(projectvalue.getProject_id());
            p.setUser_id(projectvalue.getUser_id());
            p.setChange_date(projectMapper.GetProjectdate(projectvalue.getProject_id()));
            p.setProject_progress(0);
            projectValueMapper.CreateProgress(p);
        }

        Date currentDate = new Date(System.currentTimeMillis());  //获取当前时间
        projectvalue.setChange_date(currentDate);
        projectValueMapper.CreateProgress(projectvalue);

        if(projectvalue.getProject_progress()==100){
            personnelarrangementMapper.setEnddate(projectvalue);
        }
        return new State("项目进度更新成功", projectvalue.getUser_id());
    }

    //计算某项目的各月产值
    @RequestMapping(value = "/getvalue", method = RequestMethod.POST)
    public State getProjectValue(Projectvalue projectvalue) {
        List<Projectvalue> list = projectValueMapper.GetProjectvalue(projectvalue);
        Date Dtemp = list.get(0).getChange_date();
        Integer Ptemp = list.get(0).getProject_progress();
        List<java.sql.Date> dates = new LinkedList<>();
        List<Integer> progress = new LinkedList<>();

        for(Projectvalue l : list){//获取每个月的最新进度
            if(isMonth(l.getChange_date(), Dtemp)){//如果是同一个月
                Dtemp = l.getChange_date();
                Ptemp = l.getProject_progress();
            }else {//不是同一个月
                dates.add(Dtemp);
                progress.add(Ptemp);
                Dtemp = l.getChange_date();
                Ptemp = l.getProject_progress();
            }
            if(l == list.get(list.size()-1)){//遍历到最后
                dates.add(Dtemp);
                progress.add(Ptemp);
            }
        }

        List<Integer> employee = personnelarrangementMapper.getpersonarrange(projectvalue.getProject_id());
        List<Double> cp1 = new LinkedList<>();
        List<Map<Integer,Double>> cp2 = new LinkedList<>();
        Map<Integer,Double> cp22 = new HashMap<>();
        for(int i = 1; i < 13;i++) {
            cp22.put(i, (double) 0);
        }

        for(int i = 0; i < dates.size(); i++){//计算该项目自起始日到目前每个月的产值
            double monthsalary = 0;

            //cp1.add(ComputeProject(projectMapper.GetProjectAmount(projectvalue.getProject_id()), progress.get(i), progress.get(i + 1)));

            for(int j = 0; j < employee.size(); j++){//计算该项目所有员工各月的薪资
                Map<Integer,Double> map = ComputePerson(employeeMapper.get_salary(employee.get(i)), personnelarrangementMapper.getPersonStartDate(employee.get(i)), personnelarrangementMapper.getPersonEndDate(employee.get(i)));
                cp2.add(map);
            }
            for(Map<Integer,Double> map:cp2){
                for(Map.Entry<Integer,Double> entry:map.entrySet()){
                    cp22.replace(entry.getKey(), cp22.get(entry.getKey()) + entry.getValue());
                }
            }


        }
        for(int i = 1; i < 13;i++) {
            System.out.println(cp22.get(i));
        }


        return new State("");
    }

    //判断是否是同一个月
    public boolean isMonth(Date date1, Date date2) {//
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH);
        int month2 = calendar2.get(Calendar.MONTH);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);

    }
}

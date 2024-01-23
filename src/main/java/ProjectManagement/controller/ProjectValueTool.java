package ProjectManagement.controller;

import ProjectManagement.entity.*;
import ProjectManagement.mapper.EmployeeMapper;
import ProjectManagement.mapper.PersonnelarrangementMapper;
import ProjectManagement.mapper.ProjectMapper;
import ProjectManagement.mapper.ProjectValueMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;


@CrossOrigin(origins = "*")//跨域
@RestController
public class ProjectValueTool {

    @Resource
    private ProjectValueMapper projectValueMapper;//实例化对象并注入
    @Resource
    private ProjectMapper projectMapper;//实例化对象并注入
    @Resource
    private EmployeeMapper employeeMapper;//实例化对象并注入
    @Resource
    private PersonnelarrangementMapper personnelarrangementMapper;//实例化对象并注入
    @Resource
    private PersonnelArrangeController personnelArrangeController;

    private Map<String, Object> map;

    //计算项目支撑产值
    public int ComputeProject(int amount, int progress, int current_progress){
        return (int) (amount*(current_progress-progress)*0.3*0.01);
    }
    //计算某人员各月的支撑产值
    public Map<String,Integer> ComputePerson(int salary, Date start_date, Date end_date){
        Map<String,Integer> map=new TreeMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar start = Calendar.getInstance();
        start.setTime(start_date);
        Calendar end = Calendar.getInstance();
        end.setTime(end_date);
        //计算项目起始月的薪资
        map.put(sdf.format(start.getTime()), getsalary(salary, start, getMonthLastday(start)));

        //计算项目中完整月的薪资
        start.add(Calendar.MONTH, +1);//月份加一
        while(end.after(getMonthLastday(start))) {
            start.set(Calendar.DAY_OF_MONTH, 1);//设为1号
            map.put(sdf.format(start.getTime()), getsalary(salary, start, getMonthLastday(start)));
            start.add(Calendar.MONTH, +1);//月份加一
        }

        //计算项目截止月的薪资
        start.set(Calendar.DAY_OF_MONTH, 1);//设为1号
        map.put(sdf.format(start.getTime()), getsalary(salary, start, end));
        return map;
    }
    //获取该月的最后一天的日期
    public Calendar getMonthLastday(Calendar date){
        //月份+1，天设置为0。下个月第0天，就是这个月最后一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date.getTime());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar;
    }
    //计算该时间段的薪资
    public int getsalary(int salary, Calendar date1, Calendar date2){
        Long starTime = date1.getTime().getTime();
        Long endTime = date2.getTime().getTime();
        Long days = ((endTime - starTime) / 24 / 60 / 60 / 1000);//除以一天的毫秒数
        return (int) ((double) days / 30 * salary);
    }




    //提交项目进度
    public NewState newprogress(Projectvalue projectvalue) {
        map = new TreeMap<>();

        try {
            if (projectValueMapper.existProjectvalue(projectvalue) == null) {//第一次提交项目进度时会额外提交一个进度为0提交时间为项目起始日期的项目进度
                System.out.println(projectValueMapper.GetProjectvalue(projectvalue));
                Projectvalue p = new Projectvalue();
                p.setProject_id(projectvalue.getProject_id());
                p.setUser_id(projectvalue.getUser_id());
                p.setChange_date(projectMapper.GetProjectSdate(projectvalue.getProject_id()));
                p.setProject_progress(0);
                projectValueMapper.CreateProgress(p);
            }

            Date currentDate = new Date(System.currentTimeMillis());  //获取当前时间
            projectvalue.setChange_date(currentDate);
            projectValueMapper.CreateProgress(projectvalue);

            if (projectvalue.getProject_progress() == 100) {
                personnelarrangementMapper.setEnddate(projectvalue);
            }

            map.put("user_id", projectvalue.getUser_id());
            return new NewState("200", "项目进度更新成功", map);
        }catch (Exception e){
            return new NewState("400", "项目进度更新失败");
        }
    }

    //获取该项目各月的人员产值
    public Map<String,Integer> getpersonValue(Projectvalue projectvalue) {
        List<Integer> employee = personnelarrangementMapper.getpersonarrange(projectvalue.getProject_id());
        List<Map<String,Integer>> cp2 = new LinkedList<>();
        Map<String,Integer> cp22 = getallmonth(projectMapper.GetProjectSdate(projectvalue.getProject_id()), projectMapper.GetProjectEdate(projectvalue.getProject_id()));
        //计算该项目所有员工各月的薪资
        for(int j = 0; j < employee.size(); j++){
            Map<String,Integer> map = ComputePerson(employeeMapper.get_salary(employee.get(j)), personnelarrangementMapper.getPersonStartDate(employee.get(j), projectvalue.getProject_id()), personnelarrangementMapper.getPersonEndDate(employee.get(j), projectvalue.getProject_id()));
            cp2.add(map);
        }
        for(Map<String,Integer> map:cp2){
            for(Map.Entry<String,Integer> entry:map.entrySet()){
                cp22.replace(entry.getKey(), cp22.get(entry.getKey()) + entry.getValue());
            }
        }
        return cp22;
    }

    //获取该项目各月的项目支撑产值
    public Map<String,Integer> getprojectValue(Projectvalue projectvalue) {
        map = new TreeMap<>();

        List<Projectvalue> list = projectValueMapper.GetProjectvalue(projectvalue);
        Date Dtemp = list.get(0).getChange_date();
        Integer Ptemp = list.get(0).getProject_progress();
        List<Date> dates = new LinkedList<>();
        dates.add(Dtemp);
        List<Integer> progress = new LinkedList<>();
        progress.add(Ptemp);
        int money = 0;
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
        Map<String,Integer> cp1 = new TreeMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        //计算该项目各月的支撑产值
        if(!sdf.format(dates.get(0)).equals(sdf.format(dates.get(1)))){//如果项目首月进度为0
            cp1.put(sdf.format(dates.get(0)), 0);
        }else {
            cp1.put(sdf.format(dates.get(0)), ComputeProject(projectMapper.GetProjectAmount(projectvalue.getProject_id()), 0, progress.get(0)));
        }
        for(int i = 0; i < dates.size(); i++){
            if(i + 1 >= dates.size())
                break;
            cp1.put(sdf.format(dates.get(i+1)), ComputeProject(projectMapper.GetProjectAmount(projectvalue.getProject_id()), progress.get(i), progress.get(i+1)));
        }

        return cp1;
    }

    //计算某项目的各月产值
    public Map<String,Integer> getValue(@RequestBody(required = false) Projectvalue projectvalue) {
        List<Map<String,Integer>> cp = new LinkedList<>();
        Map<String,Integer> cp22 = getallmonth(projectMapper.GetProjectSdate(projectvalue.getProject_id()), projectMapper.GetProjectEdate(projectvalue.getProject_id()));

        cp.add(getpersonValue(projectvalue));
        cp.add(getprojectValue(projectvalue));
        for(Map<String,Integer> map : cp){
            for(Map.Entry<String,Integer> entry:map.entrySet()){
                cp22.replace(entry.getKey(), cp22.get(entry.getKey()) + entry.getValue());
            }
        }
        return cp22;
    }

    //获取某年项目支撑产值
    public State getannualProjectValue(@RequestBody(required = false) Personnelarrangement personnelarrangement) {

        Map<String,Integer> map1 = getallmonth(personnelarrangement.getStart_date(), personnelarrangement.getEnd_date());//获取该年所有月，并将薪资设为0
        Projectvalue projectvalue = new Projectvalue();
        int[] id = projectValueMapper.getid();
        int money = 0;
        Map<String,Integer> cp = new TreeMap<>();
        for (int i : id) {//遍历所有项目
            projectvalue.setProject_id(i);
            cp = getprojectValue(projectvalue);
            for (Map.Entry<String, Integer> entry : cp.entrySet()) {
                try {
                    map1.replace(entry.getKey(), map1.get(entry.getKey()) + entry.getValue());
                    money+=entry.getValue();
                } catch (Exception ignored){
                }
            }
        }

        return new State("该年的项目支撑产值为：", money, map1);
    }

    //获取某年人员支撑产值
    public State getannualPersonValue(@RequestBody(required = false) Personnelarrangement personnelarrangement){

        List<Personnelarrangement> l1 = personnelArrangeController.Theyear(personnelarrangement);
        List<Personnelarrangement> l2 = personnelArrangeController.TheWholeyear(personnelarrangement);
        Map<String,Integer> map1 = getallmonth(personnelarrangement.getStart_date(), personnelarrangement.getEnd_date());//获取该年所有月，并将薪资设为0
        Map<String,Integer> m;
        int money = 0;
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        for(Personnelarrangement p : l2){//遍历今年的人员安排
            m = new TreeMap<>();
            calStart.setTime(p.getStart_date());
            calEnd.setTime(p.getEnd_date());
            m = ComputePerson(employeeMapper.get_salary(p.getEmployee_id()), new Date(calStart.getTime().getTime()), new Date(calEnd.getTime().getTime()));
            for (Map.Entry<String, Integer> entry : m.entrySet()) {
                try {
                    map1.replace(entry.getKey(), map1.get(entry.getKey()) + entry.getValue());
                    money+=entry.getValue();
                } catch (Exception ignored){
                }
            }

        }
        return new State("该年的人员支撑产值为：", money, map);
    }

    //获取某年总产值
    public NewState getannualValue(@RequestBody(required = false) Personnelarrangement personnelarrangement){
        List l1 = getannualPersonValue(personnelarrangement).getList();
        List l2 = getannualProjectValue(personnelarrangement).getList();
        List<Integer> l = new LinkedList<>();
        l.add((int) l1.get(0) + (int) l2.get(0));
        return new NewState("200", "该年总产值为：");
    }

    //获取最近三个月内立项的项目及其信息
    public List<RecentProject> getRecentpro(){
        List<RecentProject> l = new LinkedList<>();
        RecentProject r;

        Calendar data1 = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();
        data1.add(Calendar.MONTH, -3);

        //存储最近三个月内立项的项目id
        int []a = projectValueMapper.RecentProject(new Date(data1.getTime().getTime()), new Date(data2.getTime().getTime()));
        Personnelarrangement personnelarrangement;
        for(int i : a){//遍历所有项目
            r = new RecentProject();
            personnelarrangement = new Personnelarrangement();//设置立项日期和今日日期
            personnelarrangement.setStart_date(projectMapper.GetProjectSdate(i));
            personnelarrangement.setEnd_date(new Date(data2.getTime().getTime()));

            r.setProject_name(projectMapper.GetProjectname(i));//设置项目名称
            r.setStart_date(projectMapper.GetProjectSdate(i));//设置立项日期
            r.setContract_amount(projectMapper.GetProjectAmount(i));//设置合同额
            r.setProject_amount(getannualProjectValue(personnelarrangement).getValue());//获取到目前的项目支撑产值
            r.setPerson_amount(getannualPersonValue(personnelarrangement).getValue());//获取到目前的人员支撑产值
            r.setTotal_amount(r.getProject_amount()+r.getPerson_amount());//设置当前的总产值
            System.out.println(r);
            l.add(r);
        }

        return  l;
    }




    //判断是否是同一个月
    public boolean isMonth(Date date1, Date date2) {//
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);

    }

    //获取时间段内的所有月份
    public Map<String,Integer> getallmonth(Date date1, Date date2){
        Map<String,Integer> a = new TreeMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        //用Calendar 进行日期比较判断
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        while (calendar.getTime().getTime() <= date2.getTime()){
            // 把日期添加到集合
            a.put(sdf.format(calendar.getTime()), 0);
            //把日期增加一月
            calendar.add(Calendar.MONTH, 1);
            //设为1号
            calendar.set(Calendar.DAY_OF_MONTH, 1);
        }
        return a;
    }

}

package ProjectManagement.controller;

import ProjectManagement.entity.Personnelarrangement;
import ProjectManagement.entity.State;
import ProjectManagement.mapper.TimingMapper;
import ProjectManagement.service.TimingService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "*")//跨域
@RestController
public class TimingController implements TimingService {
    @Resource
    private TimingMapper timingMapper;

    //返回从当前日期有安排的员工号及其项目日期安排
    @RequestMapping(value = "/gettiming",method = RequestMethod.GET)
    public State getTime() {
        /*Date currentDate = new Date(System.currentTimeMillis());  //获取当前时间

        Calendar c = Calendar.getInstance();

        c.setTime(currentDate);//使用给定的 Date设置此日历的时间。

        c.add(Calendar.MONTH, 2);  //将当前日历时间添加两个月

        List<Personnelarrangement> list = new ArrayList<>();
       for(Personnelarrangement l : timingMapper.GetTiming(new Date(System.currentTimeMillis())))
       {
           Calendar d = Calendar.getInstance();
           d.setTime(l.getEnd_date());
           if(c.before(d)){
               Date sqldate = new Date(c.getTime().getTime());
               l.setEnd_date(sqldate);
           }
           list.add(l);
       }*/

        return new State("当前日期有安排的员工号及其项目日期安排如下", timingMapper.GetTiming(new Date(System.currentTimeMillis())));
    }
}

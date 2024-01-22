package ProjectManagement.service;

import ProjectManagement.entity.NewState;
import ProjectManagement.entity.State;

public interface TimingService {
    NewState getTime();//返回从当前日期有安排的员工号及其项目日期安排

    NewState getmap();//获取时序图
}

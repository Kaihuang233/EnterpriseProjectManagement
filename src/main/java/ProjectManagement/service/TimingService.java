package ProjectManagement.service;

import ProjectManagement.entity.State;

public interface TimingService {
    State getTime();//返回从当前日期有安排的员工号及其项目日期安排

    State getmap();//获取时序图
}

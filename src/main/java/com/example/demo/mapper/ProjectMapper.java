package com.example.demo.mapper;

import com.example.demo.entity.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProjectMapper {
    @Insert("insert into project(project_name, customer_name, project_overview, start_date, end_date, contract_amount, user_id) " +
            "values(#{project_name}, #{customer_name}, #{project_overview}, #{start_date}, #{end_date}, #{contract_amount}, #{user_id})")
    int CreateProject(Project project);

    @Select("select project_name, project_id, customer_name, project_overview, start_date, end_date, contract_amount from project where project_name=#{project_name}")
     List<Project> GetProject(Project project);

//    @Update("update 周报 set ManagerName=#{ManagerName},WeekreptCom=#{WeekreptCom},ManageID=#{ManageID} where SaleID=#{SaleID}")
//    public int udweekreport(WeeklyReport weeklyReport);


}

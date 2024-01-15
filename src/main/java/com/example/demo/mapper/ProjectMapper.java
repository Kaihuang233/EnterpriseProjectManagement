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

    //修改项目信息
    @Update("update project set project_name=#{project_name},customer_name=#{customer_name},project_overview=#{project_overview},start_date=#{start_date} ," +
            "end_date=#{end_date}, contract_amount=#{contract_amount}, user_id=#{user_id} where project_id=#{project_id}")
    int UpdateProject(Project project);


}

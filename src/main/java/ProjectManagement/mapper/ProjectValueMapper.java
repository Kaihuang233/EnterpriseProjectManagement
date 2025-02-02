package ProjectManagement.mapper;

import ProjectManagement.entity.EmployeePlus;
import ProjectManagement.entity.Project;
import ProjectManagement.entity.Projectvalue;
import ProjectManagement.entity.RecentProject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

@Mapper
public interface ProjectValueMapper {
    @Select("select * from projectvalue where project_id = #{project_id}")
    List<Projectvalue> GetProjectvalue(int project_id);//获取某项目的进度信息

    @Select("select project_id from projectvalue where project_id=#{project_id}")
     List<Integer> existProjectvalue(Projectvalue projectvalue);//查询某项目是否存在

    @Select("select change_date from projectvalue where change_date > #{change_date} && project_id = #{project_id}")
    List<Date> afterdate(Projectvalue projectvalue);//获取某时间段之后的项目日期

    @Select("select project_progress from projectvalue where change_date <= #{change_date} && project_id = #{project_id}")
    int[] lastdaybefore(Projectvalue projectvalue);//获取某年最后一天及之前的项目进度

    @Select("select project_id from project where start_date >= #{date1} && start_date <= #{date2}")
    int[] RecentProject(Date date1, Date date2);//获取最近三个月内立项的项目

    @Select("select DISTINCT project_id from projectvalue ")
    int[] getid();//获取所有项目id

    @Select("select DISTINCT project_id from project where project.project_name like (CONCAT('%',#{project_name},'%'))")
    int[] getproid(RecentProject recentProject);//获取某名称的项目id
    @Insert("insert into projectvalue(project_id, user_id, change_date, project_progress) " +
            "values(#{project_id}, #{user_id}, #{change_date}, #{project_progress})")
    int CreateProgress(Projectvalue projectvalue);//新建项目进度


}

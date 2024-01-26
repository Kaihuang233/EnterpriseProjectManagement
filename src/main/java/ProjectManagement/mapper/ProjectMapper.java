package ProjectManagement.mapper;

import ProjectManagement.entity.Project;
import ProjectManagement.entity.ProjectWithProgress;
import ProjectManagement.entity.Projectvalue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

@Mapper
public interface ProjectMapper {
    @Insert("insert into project(project_name, customer_name, project_overview, start_date, end_date, contract_amount, user_id) " +
            "values(#{project_name}, #{customer_name}, #{project_overview}, #{start_date}, #{end_date}, #{contract_amount}, #{user_id})")
    int CreateProject(Project project);

    @Select("select project_name, project_id, customer_name, project_overview, start_date, end_date, contract_amount, status from project where project_id=#{project_id}")
     List<Project> GetProject(Project project);

    @Select("select project_name, project_id, customer_name, project_overview, start_date, end_date, contract_amount, status from project where project_id=#{project_id}")
    ProjectWithProgress GetProjectUni(Project project); //id获取信息

    @Select("select project_name, project_id, status, end_date from project ")
    List<ProjectWithProgress> GetProjectList1();//无条件获取项目列表

    @Select("select project_name, project_id, status, end_date from project where project_name like (CONCAT('%',#{project_name},'%')) and status like (CONCAT('%',#{status},'%'))")
    List<ProjectWithProgress> GetProjectList2(Project project);//有条件获取项目列表

    @Select("select project_name, project_id, status, end_date from project where status = #{status}")
    List<ProjectWithProgress> GetProjectList3(Project project);//当筛选信息不为全部但名称为全部时

    @Select("select project_name, project_id, status, end_date from project where project_name like (CONCAT('%',#{project_name},'%'))")
    List<ProjectWithProgress> GetProjectList4(Project project);//当筛选信息为全部但状态不为全部时

    @Select("select project_progress from projectvalue where project_id=#{project_id}")
    List<Projectvalue> GetProjectValue(int project_id);//获取某项目的所有进度

    @Select("select project_name from project where project_id = #{project_id}")
    String GetProjectname(int project_id);

    @Select("select project_name from project where project_name = #{project_name}")
    String GetProjectname1(String project_name);

    @Select("select project_id from project where project_name = #{project_name}")
    Integer GetProject_id(String project_name);//通过名称获取id

    @Select("select start_date from project where project_id=#{project_id}")
    Date GetProjectSdate(Integer project_id);//获取项目起始日期

    @Select("select end_date from project where project_id=#{project_id}")
    Date GetProjectEdate(Integer project_id);//获取截止日期

    @Select("select start_date, end_date from project where project_id=#{project_id}")
    Project GetProjectalldate(Integer project_id);//获取起始和截止日期

    @Select("select contract_amount from project where project_id=#{project_id}")
    Integer GetProjectAmount(Integer project_id);//获取合同额

    @Select("select project_name from project where project_name=#{project_name}")
    String GetProjectName(String project_name);//获取项目名称

    //修改项目信息
    @Update("update project set project_name=#{project_name},customer_name=#{customer_name},project_overview=#{project_overview},start_date=#{start_date} ," +
            "end_date=#{end_date}, contract_amount=#{contract_amount} where project_id=#{project_id}")
    int UpdateProject(Project project);

    //修改项目状态
    @Update("update project set status=#{status} where project_id=#{project_id}")
    int UpdateProjectstatus(Project project);


}

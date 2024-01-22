package ProjectManagement.mapper;

import ProjectManagement.entity.Project;
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

    @Select("select project_name, project_id, customer_name, project_overview, start_date, end_date, contract_amount from project where project_name=#{project_name}")
     List<Project> GetProject(Project project);

    @Select("select project_name from project where project_id = #{project_id}")
    String GetProjectname(int project_id);

    @Select("select start_date from project where project_id=#{project_id}")
    Date GetProjectSdate(Integer project_id);//获取项目起始日期

    @Select("select end_date from project where project_id=#{project_id}")
    Date GetProjectEdate(Integer project_id);//获取截止日期

    @Select("select contract_amount from project where project_id=#{project_id}")
    Integer GetProjectAmount(Integer project_id);//获取合同额

    //修改项目信息
    @Update("update project set project_name=#{project_name},customer_name=#{customer_name},project_overview=#{project_overview},start_date=#{start_date} ," +
            "end_date=#{end_date}, contract_amount=#{contract_amount} where project_id=#{project_id}")
    int UpdateProject(Project project);


}

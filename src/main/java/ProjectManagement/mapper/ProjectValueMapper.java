package ProjectManagement.mapper;

import ProjectManagement.entity.Project;
import ProjectManagement.entity.Projectvalue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectValueMapper {
    @Select("select * from projectvalue where project_id=#{project_id}")
    List<Projectvalue> GetProjectvalue(Projectvalue projectvalue);//获取某项目的进度信息

    @Select("select project_id from projectvalue where project_id=#{project_id}")
     List<Integer> existProjectvalue(Projectvalue projectvalue);//查询某项目是否存在

    @Insert("insert into projectvalue(project_id, user_id, change_date, project_progress) " +
            "values(#{project_id}, #{user_id}, #{change_date}, #{project_progress})")
    int CreateProgress(Projectvalue projectvalue);//新建项目进度
}

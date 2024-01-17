package ProjectManagement.mapper;

import ProjectManagement.entity.Personnelneeds;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonnelneedsMapper {
    @Insert("insert into personnelneeds(post, number, project_id) " +
            "values(#{post}, #{number}, #{project_id})")
    int CreatePerneeds(Personnelneeds personnelneeds);

    @Select("select * from personnelneeds where project_id = #{project_id}")
    List<Personnelneeds> getinfo(int project_id);//获取该项目id的人员需求列表
}

package ProjectManagement.mapper;

import ProjectManagement.entity.Messageborad;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

@Mapper
public interface MessageBoradMapper {
    @Insert("insert into messageborad(message, project_id, type, user_id) " +
            "values(#{message}, #{project_id}, #{type}, #{user_id})")
    int CreateMessage(Messageborad messageborad);

    @Select("select type, message, user_id from messageborad where project_id = #{project_id}")
    List<Messageborad> getMessage(Integer project_id);//获取项目留言信息
}

package ProjectManagement.mapper;

import ProjectManagement.entity.Messageborad;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageBoradMapper {
    @Insert("insert into messageborad(message, project_id, type, user_id) " +
            "values(#{message}, #{project_id}, #{type}, #{user_id})")
    int CreateMessage(Messageborad messageborad);
}

package ProjectManagement.mapper;


import ProjectManagement.entity.Personnelarrangement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

@Mapper
public interface TimingMapper {
    @Select("select * from personnelarrangement where start_date <= #{date} && end_date > #{date}")//获取当前正在进行项目的员工号及日期
    List<Personnelarrangement> GetTiming(Date date);

    @Select("select DISTINCT project_id, start_date, end_date from personnelarrangement where start_date <= #{date} && end_date > #{date}")//获取当前正在进行项目的项目号及日期
    List<Personnelarrangement> Getproject(Date date);
}

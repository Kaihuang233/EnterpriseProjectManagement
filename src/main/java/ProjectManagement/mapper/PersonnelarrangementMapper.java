package ProjectManagement.mapper;

import ProjectManagement.entity.Personnelarrangement;
import ProjectManagement.entity.Project;
import ProjectManagement.entity.Projectvalue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

@Mapper
public interface PersonnelarrangementMapper {
    @Select("select * from personnelarrangement where employee_id = #{employee_id}")
    List<Personnelarrangement> getinfo(int employee_id);
    @Select("select employee_id from personnelarrangement where end_date < #{end_date} ")
    List<Integer>getperson(Personnelarrangement personnelarrangement);

    @Select("select employee_id from personnelarrangement where project_id = #{project_id} ")
    List<Integer>getpersonarrange(Integer project_id);

    @Select("select start_date from personnelarrangement where employee_id = #{employee_id} ")
    Date getPersonStartDate(Integer employee_id);//获取人员起始日期

    @Select("select end_date from personnelarrangement where employee_id = #{employee_id} ")
    Date getPersonEndDate(Integer employee_id);//获取人员截止日期

    @Insert("insert into personnelarrangement(employee_id, project_id, start_date, end_date) values(#{employee_id}, #{project_id}," +
            " #{start_date}, #{end_date})")//新建人员安排
    public int reg(Personnelarrangement personnelarrangement);

    //修改人员截止日期
    @Update("update personnelarrangement set end_date=#{change_date} where project_id=#{project_id}")
    int setEnddate(Projectvalue projectvalue);
}

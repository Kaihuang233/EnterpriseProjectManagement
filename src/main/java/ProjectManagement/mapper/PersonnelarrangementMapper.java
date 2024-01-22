package ProjectManagement.mapper;

import ProjectManagement.entity.Personnelarrangement;
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

    @Select("select start_date from personnelarrangement where employee_id = #{employee_id} && project_id = #{project_id}")
    Date getPersonStartDate(Integer employee_id, Integer project_id);//获取人员起始日期

    @Select("select end_date from personnelarrangement where employee_id = #{employee_id} && project_id = #{project_id}")
    Date getPersonEndDate(Integer employee_id, Integer project_id);//获取人员截止日期

    @Select("select * from personnelarrangement where start_date <= #{end_date}")
    List<Personnelarrangement> TheWholeyear(Personnelarrangement personnelarrangement);//获取项目日期从该年开始的项目

    @Select("select * from personnelarrangement where end_date <= #{end_date}")
    List<Personnelarrangement> Theyear(Personnelarrangement personnelarrangement);//获取某年有项目但不持续一整年的的人员安排对象

    @Select("select employee_id, start_date, end_date from personnelarrangement where project_id = #{project_id}")
    List<Personnelarrangement> getemployee(int project_id);//获取某项目的人员id和起始及截止日期

    @Select("select * from personnelarrangement where start_date <= #{start_date} && employee_id = #{employee_id}")
    List<Personnelarrangement> TheDates(Personnelarrangement personnelarrangement);//获取某员工从该日期往后的项目安排

    @Insert("insert into personnelarrangement(employee_id, project_id, start_date, end_date) values(#{employee_id}, #{project_id}," +
            " #{start_date}, #{end_date})")
     int reg(Personnelarrangement personnelarrangement);//新建人员安排

    //修改人员截止日期
    @Update("update personnelarrangement set end_date=#{change_date} where project_id=#{project_id}")
    int setEnddate(Projectvalue projectvalue);
}

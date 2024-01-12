package com.example.demo.mapper;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Personnelarrangement;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

@Mapper
public interface PersonnelarrangementMapper {
    @Select("select * from personnelarrangement where employee_id = #{employee_id}")
    List<Personnelarrangement> getinfo(int employee_id);
    @Select("select employee_id from personnelarrangement where end_date < #{end_date} ")
    List<Integer>getperson(Personnelarrangement personnelarrangement);
    @Insert("insert into personnelarrangement(employee_id, project_id, start_date, end_date) values(#{employee_id}, #{project_id}," +
            " #{start_date}, #{end_date})")//新建人员安排
    public int reg(Personnelarrangement personnelarrangement);


}

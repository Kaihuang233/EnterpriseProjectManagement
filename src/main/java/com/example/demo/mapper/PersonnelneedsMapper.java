package com.example.demo.mapper;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Personnelneeds;
import com.example.demo.entity.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonnelneedsMapper {
    @Insert("insert into personnelneeds(post, number, project_id) " +
            "values(#{post}, #{number}, #{project_id})")
    int CreatePerneeds(Personnelneeds personnelneeds);

    @Select("select post, number from personnelneeds where project_id = #{project_id}")
    List<Personnelneeds> getinfo(int project_id);//获取该项目id的人员需求列表
}

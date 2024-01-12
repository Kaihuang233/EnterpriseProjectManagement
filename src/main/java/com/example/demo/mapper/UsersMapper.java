package com.example.demo.mapper;


import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UsersMapper {
    @Select("select password from user where user_name=#{user_name}")
    public String check(String user_name);

    @Select("select user_id from user where telnum=#{telnum}")
    public int getuser_id(String Telnum);

    @Select("select telnum from user where telnum=#{Telnum}")//检查手机号是否注册过
    public String checktelnum(String Telnum);
//
//    @Select("select Type from 用户 where Telnum=#{Telnum}")
//    public String getType(String Telnum);
//
//    @Select("select UserID from 用户 where Telnum=#{Telnum}")
//    public String getid(String Telnum);//获取用户id
//
    @Insert("insert into user(user_name,telnum,password) values(#{user_name},#{telnum}, #{password})")//注册用户，一个手机号对应一个用户
    public int reg(User user);//返回值int代表插入了几条记录

    @Update("update user set telnum=#{telnum}, password = #{password} where user_id =#{user_id} ")//修改用户重要信息
    void updateimportant(User user);
    @Update("update user set code=#{code} where mail =#{mail} ")
    void updatecode(String mail,String code);

}

package ProjectManagement.mapper;


import ProjectManagement.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UsersMapper {
    @Select("select password from user where user_name=#{user_name}")
    public String check(String user_name);

    @Select("select user_id from user where user_name=#{user_name}")
    Integer getuser_id(String user_name);//通过姓名获取id

    @Select("select user_id from user where telnum=#{telnum}")
    Integer getuser_idByphone(String telnum);//通过电话获取id

    @Select("select telnum from user where telnum=#{Telnum}")//检查手机号是否注册过
     String checktelnum(String Telnum);

    @Select("select user_id from employee where mail=#{mail}")//检查邮箱是否注册过
     String checkmail(String mail);

    @Select("select user_name from user where user_name=#{user_name}")//检查用户名是否注册过
     String checkname(String user_name);
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


}

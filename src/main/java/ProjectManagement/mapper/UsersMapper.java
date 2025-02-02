package ProjectManagement.mapper;


import ProjectManagement.entity.Employee;
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

    @Select("select user_name from user where user_id=#{user_id}")
    String getuser_name(int user_id);//通过姓名获取id

    @Select("select user_id from user where telnum=#{telnum}")
    Integer getuser_idByphone(String telnum);//通过电话获取id

    @Select("select telnum from user where telnum=#{Telnum}")//检查手机号是否注册过
     String checktelnum(String Telnum);

    @Select("select user_id from employee where mail=#{mail}")//检查邮箱是否注册过
     String checkmail(String mail);

    @Select("select name, sex, age, city, telnum, mail from employee where user_id=#{user_id}")//获取登陆者信息
    Employee getinfo(Integer user_id);

    @Select("select password from user where user_id=#{user_id}")//获取密码
    String getpassword(Integer user_id);

    @Select("select user_name from user where user_name=#{user_name}")//检查用户名是否注册过
     String checkname(String user_name);

    @Select("select mail from employee where user_id=#{user_id}")//检查用户名是否注册过
    String findmail(int user_id);

    @Insert("insert into user(user_name,telnum,password) values(#{user_name},#{telnum}, #{password})")//注册用户，一个手机号对应一个用户
    public int reg(User user);//返回值int代表插入了几条记录

    @Update("update user set telnum=#{telnum}, password = #{password} where user_id =#{user_id} ")//修改用户重要信息
    void updateimportant(User user);

    @Update("update user set password=#{password} where user_id =#{user_id} ")//修改密码
    void updatepassword(int user_id, String password);

}

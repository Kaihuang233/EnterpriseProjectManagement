package ProjectManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import ProjectManagement.entity.Employee;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Insert("insert into employee(name,telnum,mail,age,post,type,salary,sex,city,entry_date)" +
            " values(#{name}, #{telnum}, #{mail}, #{age}, #{post}, #{type}," +
            " #{salary}, #{sex}, #{city}, #{entry_date})")
    int CreateEmployee(Employee employee);//新增职员

    @Select("select name,type,post,salary,sex from Employee where telnum = #{telnum}")
    List<Employee> getinfo(String telnum);

    @Select("select * from Employee")
    List<Employee> getemplist();//获取职员列表

    @Update("update employee set user_id=#{user_id} where telnum =#{mail}")//将职员与用户绑定
    int Set_user(String mail, int user_id);

    @Update("update employee set telnum=#{telnum} where user_id =#{user_id}")//将职员手机号修改
    int Set_tel(String telnum, int user_id);

    @Update("update employee set mail=#{mail} where user_id =#{user_id}")//将职员手机号修改
    int Set_mail(String mail, int user_id);
    @Update("update employee set age=#{age}, sex=#{sex}, city=#{city} where user_id =#{user_id} ")//修改职员基本信息
    void updatebasic(Employee employee);
    @Select("select type from employee where user_id = #{user_id}")
    String get_type(int user_id);

    @Select("select name from employee where user_id = #{user_id}")
    String get_name(int user_id);

    @Select("select salary from employee where employee_id = #{employee_id}")
    int get_salary(int employee_id);//获取职员薪资

    @Select("select code from employee where mail=#{mail}")
    String checkcode(String mail);//返回验证码

    @Select("select mail from employee where mail=#{mail}")//查找邮箱是否存在
    String check(String mail);

    @Update("update employee set code=#{code} where mail =#{mail}")//更新验证码
    void updatecode(String mail, String code);
}

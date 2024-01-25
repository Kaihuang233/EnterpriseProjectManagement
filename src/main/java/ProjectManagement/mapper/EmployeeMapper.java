package ProjectManagement.mapper;

import ProjectManagement.entity.EmployeePlus;
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

    @Select("select employee_id from Employee")
    List<Integer> getallId();//获取所有职员id

    @Select("select employee_id, name, telnum, age, type, salary, sex, entry_date, city, post, user_id from Employee")
    List<Employee> getemplist();//获取职员列表

    @Select("select employee_id, name, telnum, age, type, salary, sex, entry_date, city, post, user_id from Employee")
    List<EmployeePlus> getemplistPlus();//获取plus职员列表

    @Select("select employee_id, name, telnum, age, type, salary, sex, entry_date, city, post, user_id, mail from Employee where name like (CONCAT('%',#{name},'%'))")
    List<EmployeePlus> getemplistPlus1(EmployeePlus employeePlus);//获取plus职员列表

    @Select("select employee_id, name, telnum, age, type, salary, sex, entry_date from Employee where post = #{post}")
    List<Employee> getemplist1(String post);//根据职位获取职员列表

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

    @Select("select mail from employee where user_id = #{user_id}")
    String get_mail(int user_id);//获取邮箱

    @Select("select name from employee where employee_id = #{employee_id}")
    String get_nameByemp_id(int employee_id);//通过职员id获取姓名

    @Select("select salary from employee where employee_id = #{employee_id}")
    int get_salary(int employee_id);//获取职员薪资

    @Select("select post from employee where employee_id = #{employee_id}")
    String get_post(int employee_id);//获取职员职位

    @Select("select code from employee where mail=#{mail}")
    String checkcode(String mail);//返回验证码

    @Select("select code from employee where user_id=#{user_id}")
    String checkcode1(int user_id);//返回验证码

    @Select("select mail from employee where mail=#{mail}")//查找邮箱是否存在
    String check(String mail);

    @Select("select name, telnum, mail, age, post, type, salary, sex, city, entry_date from employee where name=#{name} || post=#{post}")
    List<Employee> getinfo1(Employee employee);//通过姓名或职位查找员工信息

    @Select("select name, telnum, mail, age, post, type, salary, sex, city, entry_date from employee where name=#{name} && post=#{post}")
    List<Employee> getinfo2(Employee employee);//通过姓名和职位查找员工信息

    @Update("update employee set code=#{code} where mail =#{mail}")//更新验证码
    void updatecode(String mail, String code);

    @Update("update employee set post = #{post}, type = #{type}, salary = #{salary} where employee_id =#{employee_id}")//将员工设为离职
    void setemployee(Employee employee);

    @Update("update employee set post = #{post}, type = #{type}, salary = #{salary} where employee_id =#{employee_id}")//修改某员工信息
    void Upemployee(Employee employee);
}

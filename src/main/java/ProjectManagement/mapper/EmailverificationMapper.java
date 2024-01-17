package ProjectManagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EmailverificationMapper {
    @Insert("insert into emailverification(mail,code) values(#{mail}, #{code})")//新建邮箱及其验证码
    public int reg(String mail, String code);//返回值int代表插入了几条记录

    @Select("select code from emailverification where mail=#{mail}")//返回验证码
    public String checkcode(String mail);

    @Select("select mail from emailverification where mail=#{mail}")//查找邮箱是否存在
    public String check(String mail);

    @Update("update emailverification set code=#{code} where mail =#{mail}")//更新验证码
    void updatecode(String mail, String code);
}

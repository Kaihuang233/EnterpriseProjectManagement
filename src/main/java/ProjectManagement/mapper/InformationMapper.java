package ProjectManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InformationMapper {
    @Select("select username from information where id = 1")
    String getusername();

    @Select("select password from information where id = 1")
    String getpassword();

    @Select("select mailusername from information where id = 1")
    String getmailusername();

    @Select("select mailpass from information where id = 1")
    String getmailpass();
}

package ProjectManagement.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Transform {
    public static java.sql.Date trans(String date){
        try {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = ft.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            return new java.sql.Date(cal.getTime().getTime());
        }catch (Exception e){
            return new java.sql.Date(Calendar.getInstance().getTime().getTime());
        }
    }
}

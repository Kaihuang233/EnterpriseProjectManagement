package ProjectManagement.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

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

    //Object转Map
//    public static Map<String, Object> getObjectToMap(Object obj) {
//        Map<String, Object> map = new TreeMap<>();
//        try {
//            Class<?> cla = obj.getClass();
//            java.lang.reflect.Field[] fields = cla.getDeclaredFields();
//            for (Field field : fields) {
//                field.setAccessible(true);
//                String keyName = field.getName();
//                Object value = field.get(obj);
//                if (value == null)
//                    value = "";
//                map.put(keyName, value);
//            }
//            return map;
//        }catch (IllegalAccessException e){
//            map.put("Error", e);
//            return map;
//        }
//    }
    //Object转Map
    public static Map<String, Object> getObjectToMap(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(object), Map.class);
    }

    //Object 转 List
    public static <T> List<T> castList(Object obj, Class<T> clazz)
    {
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>)
        {
            for (Object o : (List<?>) obj)
            {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    //Map<String, Object>转Map<String, Integer>
    public  static Map<String, Integer> copyToStringValueMap(Map<String, Object> input) {
        Map<String, String> ret = new TreeMap<>();
        Map<String, Integer> end = new TreeMap<>();
        for (Map.Entry<String, Object> entry : input.entrySet()) {
            System.out.println(entry.getKey()+" "+ entry.getValue());
            ret.put(entry.getKey(), (String) entry.getValue());
        }
        System.out.println(ret);
        for(Map.Entry<String, String> entry : ret.entrySet()){
            end.put(entry.getKey(), Integer.valueOf(entry.getValue()));
        }

        return end;
    }


}

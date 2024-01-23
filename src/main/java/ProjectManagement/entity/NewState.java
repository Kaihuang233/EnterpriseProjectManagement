package ProjectManagement.entity;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.*;

@Data
public class NewState {
    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "msg")
    private String msg;

    @JsonProperty(value = "data")
    private Object data;


    public NewState(String code){
        this.code = code;
    }

    public NewState(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public NewState(String code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public void test(){
        Map<String, Object> map = new HashMap<>();
        List<Integer> listt = new ArrayList<>();
        Map<String,String> Smap = new HashMap<>();
        map.put("ID", 123);
        map.put("name", "123");
        map.put("list", listt);
        map.put("Smap", Smap);
    }
}

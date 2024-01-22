package ProjectManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class State {
    private String msg;

    @JsonProperty(value = "user_id")
    private int user_id=-1;
    @JsonProperty(value = "information")
    private String information="";
    @JsonProperty(value = "list")
    private List list;
    @JsonProperty(value = "map")
    private Map map;
    public State(String msg){
        this.msg = msg;
    }
    public State(String msg, int user_id){
        this.msg = msg;
        this.user_id = user_id;
    }
    public State(String msg, int user_id, String information){
        this.msg = msg;
        this.user_id = user_id;
        this.information = information;
    }

    public State(String msg, String information){
        this.msg = msg;
        this.information = information;
    }

    public State(String msg, List list){
        this.msg = msg;
        this.list = list;
    }

    public State(String msg, Map map){
        this.msg = msg;
        this.map = map;
    }
}

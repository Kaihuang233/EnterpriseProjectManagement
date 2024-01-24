package ProjectManagement.service;

import ProjectManagement.entity.Messageborad;
import ProjectManagement.entity.NewState;
import ProjectManagement.entity.State;
import org.springframework.web.bind.annotation.RequestBody;

public interface MessageboradService {
    NewState NewMessageborad(Messageborad messageborad);//发表留言

    NewState GetMessageborad(Messageborad messageborad);//获取某项目留言
}

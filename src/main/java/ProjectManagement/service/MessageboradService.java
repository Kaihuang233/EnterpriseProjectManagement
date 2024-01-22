package ProjectManagement.service;

import ProjectManagement.entity.Messageborad;
import ProjectManagement.entity.NewState;
import ProjectManagement.entity.State;

public interface MessageboradService {
    NewState NewMessageborad(Messageborad messageborad);//发表留言
}

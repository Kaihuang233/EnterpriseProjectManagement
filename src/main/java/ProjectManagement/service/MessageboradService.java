package ProjectManagement.service;

import ProjectManagement.entity.Messageborad;
import ProjectManagement.entity.State;

public interface MessageboradService {
    State NewMessageborad(Messageborad messageborad);//发表留言
}

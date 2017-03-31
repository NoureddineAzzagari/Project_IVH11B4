package sample.web.ui.Service.interfaces;

import sample.web.ui.domain.User.User;

public interface IUserService {
    boolean authenticate(String userName, String password);
    void addUser(User user);
    User getUser(String userName, String password);
}

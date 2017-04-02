package sample.web.ui.Service.interfaces;

import sample.web.ui.domain.User.User;
import sample.web.ui.viewModel.LoginViewModel;

public interface IUserService {
    LoginViewModel authenticate(String userName, String password);
    void addUser(User user);
    User getUser(String userName, String password);
    User getUser(long id);
    void updateFavourites(long userId, long movieId);
}

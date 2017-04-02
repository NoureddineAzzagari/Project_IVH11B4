package sample.web.ui.viewModel;

import lombok.Getter;

/**
 * Created by Ids van der Zee on 2-4-2017.
 */
@Getter
public class LoginViewModel {
    private boolean authenticated;
    private long userId;

    public LoginViewModel(boolean authenticated, long userId) {
        this.authenticated = authenticated;
        this.userId = userId;
    }
}

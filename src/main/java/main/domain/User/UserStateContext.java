package main.domain.User;


public class UserStateContext implements UserStateInterface {

    private UserStateInterface state;

    @Override
    public void logged() {
        this.state.logged();
    }

    public UserStateInterface getState() {
        return state;
    }

    public void setState(UserStateInterface state) {
        this.state = state;
    }
}

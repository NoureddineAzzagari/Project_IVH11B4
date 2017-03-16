package sample.web.ui.domain.User;

public class Offline implements UserStateInterface {

    @Override
    public void logged() {
        System.out.println("You are offline");
    }
}

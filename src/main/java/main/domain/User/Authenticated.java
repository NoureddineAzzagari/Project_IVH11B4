package main.domain.User;

public class Authenticated implements UserStateInterface {

    @Override
    public void logged() {
      System.out.println("You successfully logged in");
    }
}

package sample.web.ui.viewModel;

import lombok.Getter;

/**
 * Created by Ids van der Zee on 30-3-2017.
 */
@Getter
public class UserInfo {
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final boolean admin;
    private final int phone;

    public UserInfo(String userName, String firstName, String lastName, String email, String address, boolean admin, int phone) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.admin = admin;
        this.phone = phone;
    }
}

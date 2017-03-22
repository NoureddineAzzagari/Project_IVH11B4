package sample.web.ui.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.web.ui.Service.concrete.UserService;
import sample.web.ui.domain.User.User;

/**
 * Created by Ids van der Zee on 22-3-2017.
 */
@CrossOrigin
@RestController
@RequestMapping("/")
public class MemberController {

    private final UserService userService;

    public MemberController(UserService userService) {
        this.userService = userService;
    }

    /**
     * methode die login gegevens controleerd
     * @param userName: gebruikers naam
     * @param password: wachtwoord
     * @return true als correct anders false
     */
    @RequestMapping("/login")
    public boolean Authenticate(String userName, String password){
        return userService.authenticate(userName, password);
    }

    /**
     * methode die een nieuw account aanmaakt
     * @param firstName voornaam
     * @param lastName achternaam
     * @param address adres
     * @param emailAddress email
     * @param userName gebruikers naam
     * @param password wachtwoord
     * @param age leeftijd
     * @param telephoneNumber tel nr
     */
    @RequestMapping("/signup")
    public  void  signUp(String firstName, String lastName, String address,
                         String emailAddress, String userName,
                         String password, int age, int telephoneNumber){
        User.UserBuilder userBuilder = new User.UserBuilder(firstName, lastName, address, age,
                userName, password,emailAddress, false);
        if(telephoneNumber > 0) userBuilder.telephoneNumber(telephoneNumber);
        User user = userBuilder.build();
        userService.addUser(user);
    }
}

package sample.web.ui.Service.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.Service.interfaces.IUserService;
import sample.web.ui.dataAcces.UserRepository;
import sample.web.ui.domain.User.Authenticated;
import sample.web.ui.domain.User.User;
import sample.web.ui.domain.User.UserStateContext;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * controleerd of de opgegeven inlognaam en wachtwoord overeen komen met een user in de db
     * @param userName inlognaam
     * @param password wachtwoord
     * @return true als user bestaat met deze username wachtwoord combinatie
     */
    public boolean authenticate(String userName, String password){
        for (User user: userRepository.findAll()){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                UserStateContext userStateContext = new UserStateContext();
                userStateContext.setState(new Authenticated());
                return true;
            }
        }
        return false;
    }

    /**
     * voegt een nieuwe user toe in de db
     * @param user toe te voegen user
     */
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(String userName, String password){
        for(User user: userRepository.findAll()){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)) return user;
        }
        return null;
    }
}

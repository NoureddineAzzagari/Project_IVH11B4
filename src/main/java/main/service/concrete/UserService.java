package main.service.concrete;

import main.dataAcces.UserRepository;
import main.domain.User.Authenticated;
import main.domain.User.Offline;
import main.domain.User.User;
import main.domain.User.UserStateContext;
import main.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ids on 15-3-2017.
 */
@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authenticate(String userName, String password) {
        UserStateContext stateContext = new UserStateContext();
        for(User user : userRepository.findAll()){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                stateContext.setState(new Authenticated());
                return true;
            }
        }
        stateContext.setState(new Offline());
        return false;
    }
}

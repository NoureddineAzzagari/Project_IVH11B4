package sample.web.ui.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.ServiceInterfaces.IUserService;
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
}

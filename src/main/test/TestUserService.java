import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import sample.web.ui.Service.concrete.MovieService;
import sample.web.ui.Service.concrete.UserService;
import sample.web.ui.dataAcces.UserRepository;
import sample.web.ui.domain.User.User;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

/**
 * Created by Ids van der Zee on 3-4-2017.
 */
public class TestUserService {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testAuthenticateWrongCredentials(){

        ArrayList<User> testUsers = new ArrayList<>();
        testUsers.add(new User.UserBuilder("firstname", "lastname", "address", 1, "username", "password", "email", false).build());

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(testUsers);

        MovieService movieService = Mockito.mock(MovieService.class);

        UserService service = new UserService(userRepository, movieService);

        Assert.assertFalse(service.authenticate("wrongUserName", "wrongPassword").isAuthenticated());
    }

    @Test
    public void testAuthenticateCorrectCredentials(){
        ArrayList<User> testUsers = new ArrayList<>();
        testUsers.add(new User.UserBuilder("firstname", "lastname", "address", 1, "username", "password", "email", false).build());

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(testUsers);

        MovieService movieService = Mockito.mock(MovieService.class);

        UserService service = new UserService(userRepository, movieService);

        Assert.assertTrue(service.authenticate("username", "password").isAuthenticated());
    }
}


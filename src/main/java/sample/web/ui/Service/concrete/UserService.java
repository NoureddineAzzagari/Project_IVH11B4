package sample.web.ui.Service.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.Service.interfaces.IMovieService;
import sample.web.ui.Service.interfaces.IUserService;
import sample.web.ui.dataAcces.UserRepository;
import sample.web.ui.domain.Favourites;
import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.domain.User.Authenticated;
import sample.web.ui.domain.User.User;
import sample.web.ui.domain.User.UserStateContext;
import sample.web.ui.domain.tvShow.TvShow;
import sample.web.ui.viewModel.LoginViewModel;

import java.util.ArrayList;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final IMovieService movieService;

    @Autowired
    public UserService(UserRepository userRepository, IMovieService movieService) {
        this.userRepository = userRepository;
        this.movieService = movieService;
    }

    /**
     * controleerd of de opgegeven inlognaam en wachtwoord overeen komen met een user in de db
     * @param userName inlognaam
     * @param password wachtwoord
     * @return true als user bestaat met deze username wachtwoord combinatie
     */
    public LoginViewModel authenticate(String userName, String password){
        for (User user: userRepository.findAll()){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                UserStateContext userStateContext = new UserStateContext();
                userStateContext.setState(new Authenticated());
                return new LoginViewModel(true, user.getId());
            }
        }
        return new LoginViewModel(false, -1);
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

    /**
     * haalt een user op aan de hand van een id
     * @param id id van de op te halen user
     * @return user
     */
    @Override
    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    /**
     * voegt een nieuwe film toe aan favorites
     * @param userId id van de user waarvoor de film wordt toegevoegd
     * @param movieId id van de toe te voegen film
     */
    @Override
    public void updateFavourites(long userId, long movieId) {
        BaseMovie movie = movieService.getMovieById(movieId);
        User user = userRepository.findOne(userId);
        if(movie != null && user != null) {
            if (user.getFavourites() == null || user.getFavourites().getMovies() == null) {
                ArrayList<BaseMovie> movies = new ArrayList<>();
                movies.add(movie);
                user.setFavourites(new Favourites(movies, new ArrayList<TvShow>()));
            } else {
                Favourites fav = user.getFavourites();
                fav.getMovies().add(movie);
                user.setFavourites(fav);
            }
            userRepository.save(user);
        }
    }
}

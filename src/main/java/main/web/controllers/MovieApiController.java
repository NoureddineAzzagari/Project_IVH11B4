package main.web.controllers;

import main.domain.Configuration;
import main.domain.Movie.BaseMovie;
import main.domain.Movie.Movie;
import main.domain.tvShow.TvShow;
import main.domain.User.UserStateContext;
import main.service.concrete.MovieService;
import main.service.concrete.TvShowService;
import main.service.concrete.UserService;
import main.service.interfaces.IMovieService;
import main.service.interfaces.ITvShowService;
import main.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RequestMapping("/api")
@RestController
@CrossOrigin
public class MovieApiController {

    private IMovieService movieService;
    private ITvShowService tvShowService;
    private  IUserService userService;

    @Autowired
    public MovieApiController(IMovieService movieService, ITvShowService tvShowService, IUserService userService) {
        this.movieService = movieService;
        this.tvShowService = tvShowService;
        this.userService = userService;
    }

    @RequestMapping("/config")
    public Configuration getConfiguration(){
        return Configuration.getConfiguration();
    }

    @RequestMapping("/login")
    public boolean login(String userName, String password){
        return userService.authenticate(userName, password);
    }

    @RequestMapping("/movies")
    public Iterable<BaseMovie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @RequestMapping("/movies/{id}")
    public BaseMovie getMovieById(@PathVariable("id") Long id){
        return movieService.getMovieById(id);
    }
    @RequestMapping("/tv")
    public Iterable<TvShow> getAllShows(){
        return tvShowService.getAllTvShows();
    }


}

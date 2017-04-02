package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sample.web.ui.Service.interfaces.IMovieService;
import sample.web.ui.Service.interfaces.IUserService;
import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.domain.Movie.memento.CareTaker;
import sample.web.ui.domain.Movie.memento.Memento;
import sample.web.ui.domain.Movie.memento.Originator;
import sample.web.ui.viewModel.MovieViewModel;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ids van der Zee on 22-3-2017.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

    private final IMovieService movieService;
    private final IUserService userService;

    @Autowired
    public MovieApiController(IMovieService movieService, IUserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    /**
     * methode om alle films mee op te halen
     * @return alle beschikbare films
     */
    @RequestMapping("")
    public MovieViewModel getAllMovies(@CookieValue(value = "userInfo", defaultValue = "")String userCookie){
        try{
            if(!movieService.checkForMovies()) fillMovieDb();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(userCookie != null && !userCookie.equals("")) return movieService.getAllMovies(Long.valueOf(userCookie.split("~~~")[3]));
        return null;
    }

    /**
     * voegt een film toe aan de favorieten
     * @param userCookie cookie met info over de user
     * @param id id van de film
     */
    @RequestMapping("addtofavourites/{id}")
    public void addToFavourites(@CookieValue(value = "userInfo")String userCookie, @PathVariable("id") Long id){
        userService.updateFavourites(Long.valueOf(userCookie.split("~~~")[3]), id);
    }

    /**
     * toont films die voldoen aan de megeven zoek opties
     * @param searchString de text waarop gezocht wordt
     * @param searchOption of er gezocht moet worden op titel of op acteur
     * @return lijst met films
     */
    @RequestMapping("/search")
    public Iterable<BaseMovie> searchMovies(String searchString, int searchOption){
        return movieService.searchMovies(searchString, searchOption);
    }

    /**
     * methode om een film op te halen aan de hand van een id
     * @param id id van de op te halen film
     * @return alle informatie over de opgevraagde film
     */
    @RequestMapping("/{id}")
    public BaseMovie getMovieById(@PathVariable("id") long id, HttpServletRequest req){
        CareTaker careTaker;
        if(req.getSession().getAttribute("memento") != null){
            careTaker = (CareTaker) req.getSession().getAttribute("memento");
        }
        else careTaker = new CareTaker();

        Originator originator = new Originator();

        careTaker.getSavedStates().forEach((Memento memento) -> {
            if(memento.getState() != id){
                originator.setState(id);
                careTaker.addMemento(originator.saveToMemento());

                req.getSession().setAttribute("memento", careTaker);
            }
        });

        return movieService.getMovieById(id);
    }

    /**
     * haalt alle recent bekeken films op
     * @param req httprequest
     * @return lijst met films
     */
    @RequestMapping("/recent")
    public Iterable<BaseMovie> getRecentMovies(HttpServletRequest req){
        CareTaker careTaker = (CareTaker)req.getSession().getAttribute("memento");
        ArrayList<BaseMovie> movies = new ArrayList<>();
        careTaker.getSavedStates().forEach((Memento state) -> {
            long id = state.getState();
            movies.add(movieService.getMovieById(id));
        });
        return movies;
    }

    /**
     * haalt informatie op over een film aan de hand van de titel en het jaar van de omdb api
     * http://www.omdbapi.com/
     * plaatst de nieuwe film daarna in de db
     * @param title titel van de film
     * @param year jaar dat de film uit is gekomen
     */
    @RequestMapping("/add")
    public void addMovie(String title, int year){
        movieService.addMovie(movieService.getNewMovie(title, year));
    }

    /**
     * methode om films in de database te zetten
     */
    private void fillMovieDb(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Logan", 2017);
        map.put("Kong:+Skull+Island", 2017);
        map.put("Beauty+and+the+Beast", 2017);
        map.put("Pirates+of+the+Caribbean:+Dead+Men+Tell+No+Tales", 2017);
        map.put("Thor:+Ragnarök", 2017);

        map.forEach((String title, Integer year) -> {
            BaseMovie movie = movieService.getNewMovie(title, year);
            if(movie != null) movieService.addMovie(movie);
        });
    }
}

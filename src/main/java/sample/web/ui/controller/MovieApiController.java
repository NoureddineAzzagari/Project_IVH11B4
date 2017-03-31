package sample.web.ui.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.web.ui.Service.concrete.MovieService;
import sample.web.ui.domain.Movie.BaseMovie;
import sun.misc.IOUtils;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Ids van der Zee on 22-3-2017.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

    private final MovieService movieService;

    @Autowired
    public MovieApiController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * methode om alle films mee op te halen
     * @return alle beschikbare films
     */
    @RequestMapping("")
    public Iterable<BaseMovie> getAllMovies(){
        try{
            if(!movieService.checkForMovies()) fillMovieDb();
        }catch (Exception e){
            e.printStackTrace();
        }
        return movieService.getAllMovies();
    }

    /**
     * methode om een film op te halen aan de hand van een id
     * @param id id van de op te halen film
     * @return alle informatie over de opgevraagde film
     */
    @RequestMapping("/{id}")
    public BaseMovie getMovieById(@PathVariable("id") long id){
        return movieService.getMovieById(id);
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

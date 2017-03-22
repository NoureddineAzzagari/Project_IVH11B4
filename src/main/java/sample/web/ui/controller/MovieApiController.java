package sample.web.ui.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.web.ui.Service.concrete.MovieService;
import sample.web.ui.domain.Movie.BaseMovie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Created by Ids van der Zee on 22-3-2017.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

    private final MovieService movieService;

    public MovieApiController(MovieService movieService) {
        this.movieService = movieService;
        try{
            if(movieService.checkForMovies())extractMovies();
        }catch (Exception e){

        }

    }

    /**
     * methode om alle films mee op te halen
     * @return alle beschikbare films
     */
    @RequestMapping("")
    public Iterable<BaseMovie> getAllMovies(){
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
     * methode om films in de database te zetten omdat jpa na het starten van de applicatie de db leeg gooit
     */
    private void fillMovieDb(HashMap<String, Integer> map){
        map.forEach((String title, Integer year) -> {
            BaseMovie movie = movieService.getNewMovie(title, year);
            if(movie != null) movieService.addMovie(movie);
        });
    }

    /**
     * methode om films op te halen uit de movies.json file en deze vervolgens in de db te schrijven
     */
    private void extractMovies() throws Exception{
        HashMap<String, Integer> map = new HashMap<>();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("movies.json").getFile());
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder builder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while((line = reader.readLine()) != null){
            builder.append(line);
            builder.append(ls);
        }
        String fileString = builder.toString();
        reader.close();
        JSONArray jsonArray = new JSONArray(fileString);

        for(Object obj: jsonArray){
            String title = ((JSONObject) obj).getString("title");
            int year = ((JSONObject) obj).getInt("year");
            map.put(title, year);
        }
        fillMovieDb(map);

    }
}

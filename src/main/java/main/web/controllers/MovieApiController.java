package main.web.controllers;

import main.domain.BaseMovie;
import main.domain.Movie;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class MovieApiController {

    @CrossOrigin
    @RequestMapping("/movies")
    public ArrayList<BaseMovie> getAllMovies() {

        ArrayList<BaseMovie> movies = new ArrayList<>();

        movies.add(new Movie(
                "Get Out",
                "A young African-American man visits his Caucasian girlfriend's mysterious family estate.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNTE2Nzg1NjkzNV5BMl5BanBnXkFtZTgwOTgyODMyMTI@._V1_SY1000_CR0,0,631,1000_AL_.jpg",
                120
                ));
        movies.add(new Movie(
                "Collide",
                "An American backpacker gets involved with a ring of drug smugglers as their driver, though he winds up on the run from his employers across Cologne high-speed Autobahn.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNjRhMGI3ZGItZTMzMS00NzdmLWI1MzMtNjk0ZmY5ZjMyZDdkL2ltYWdlXkEyXkFqcGdeQXVyNTQ3MjE4NTU@._V1_SY1000_CR0,0,674,1000_AL_.jpg",
                120
        ));
        return movies;
    }

    @CrossOrigin
    @RequestMapping("/movies/{id}")
    public BaseMovie getMovieById(){
        return new Movie(
                "Collide",
                "An American backpacker gets involved with a ring of drug smugglers as their driver, though he winds up on the run from his employers across Cologne high-speed Autobahn.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNjRhMGI3ZGItZTMzMS00NzdmLWI1MzMtNjk0ZmY5ZjMyZDdkL2ltYWdlXkEyXkFqcGdeQXVyNTQ3MjE4NTU@._V1_SY1000_CR0,0,674,1000_AL_.jpg",
                120
        );
    }
}

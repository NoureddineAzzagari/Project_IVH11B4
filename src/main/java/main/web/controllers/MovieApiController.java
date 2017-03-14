package main.web.controllers;

import main.dataAcces.*;
import main.domain.Movie.BaseMovie;
import main.domain.Movie.Movie;
import main.domain.tvShow.TvShow;
import main.domain.User.UserStateContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RequestMapping("/api")
@RestController
@CrossOrigin
public class MovieApiController {


    private final BaseMovieRepository movieRepository;
    private final EpisodeRepository episodeRepository;
    private final SeasonRepository seasonRepository;
    private final TvShowRepository tvShowRepository;
    private final UserRepository userRepository;

    @Autowired
    public MovieApiController(BaseMovieRepository movieRepository, EpisodeRepository episodeRepository, SeasonRepository seasonRepository, TvShowRepository tvShowRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.episodeRepository = episodeRepository;
        this.seasonRepository = seasonRepository;
        this.tvShowRepository = tvShowRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/login")
    public boolean login(String userName, String password){

        UserStateContext userStateContext = new UserStateContext();


        return true;
    }

    @RequestMapping("/movies")
    public Iterable<BaseMovie> getAllMovies() {

        ArrayList<BaseMovie> movies = new ArrayList<>();

        movies.add(new Movie(
                "Logan",
                "In the near future, a weary Logan cares for an ailing Professor X in a hide out on the Mexican border. But Logan's attempts to hide from the world and his legacy are up-ended when a young mutant arrives, being pursued by dark forces.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMjI1MjkzMjczMV5BMl5BanBnXkFtZTgwNDk4NjYyMTI@._V1_SY1000_CR0,0,676,1000_AL_.jpg",
                123,
                "2017"
        ));
        movies.add(new Movie(
                "The Shack",
                "A grieving man receives a mysterious, personal invitation to meet with God at a place called \"The Shack.\"",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMjI3MDMxNzcxNl5BMl5BanBnXkFtZTgwODc4MzkwOTE@._V1_SY1000_CR0,0,647,1000_AL_.jpg",
                123,
                "2017"
        ));
        movies.add(new Movie(
                "Before I Fall",
                "February 12 is just another day in Sam's charmed life until it turns out to be her last. Stuck reliving her last day over one inexplicable week, Sam untangles the mystery around her death and discovers everything she's in danger of losing.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNDYwOTY0MDI2OV5BMl5BanBnXkFtZTgwOTE5NzM2MDI@._V1_SY1000_SX675_AL_.jpg",
                123,
                "2017"
        ));
        movies.add(new Movie(
                "Get Out",
                "A young African-American man visits his Caucasian girlfriend's mysterious family estate.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNTE2Nzg1NjkzNV5BMl5BanBnXkFtZTgwOTgyODMyMTI@._V1_SY1000_CR0,0,631,1000_AL_.jpg",
                120,
                "2016"
                ));
        movies.add(new Movie(
                "Collide",
                "An American backpacker gets involved with a ring of drug smugglers as their driver, though he winds up on the run from his employers across Cologne high-speed Autobahn.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNjRhMGI3ZGItZTMzMS00NzdmLWI1MzMtNjk0ZmY5ZjMyZDdkL2ltYWdlXkEyXkFqcGdeQXVyNTQ3MjE4NTU@._V1_SY1000_CR0,0,674,1000_AL_.jpg",
                120,
                "2017"
        ));
        return movies;
    }

    @RequestMapping("/movies/{id}")
    public BaseMovie getMovieById(){
        return new Movie(
                "Collide",
                "An American backpacker gets involved with a ring of drug smugglers as their driver, though he winds up on the run from his employers across Cologne high-speed Autobahn.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNjRhMGI3ZGItZTMzMS00NzdmLWI1MzMtNjk0ZmY5ZjMyZDdkL2ltYWdlXkEyXkFqcGdeQXVyNTQ3MjE4NTU@._V1_SY1000_CR0,0,674,1000_AL_.jpg",
                120,
               "2015"
        );
    }
    @RequestMapping("/tv")
    public Iterable<TvShow> getAllShows(){
        ArrayList<TvShow> shows = new ArrayList<>();
        shows.add(new TvShow(
                null,
                "show1",
                "beschrijving 1",
                "2016",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNjRhMGI3ZGItZTMzMS00NzdmLWI1MzMtNjk0ZmY5ZjMyZDdkL2ltYWdlXkEyXkFqcGdeQXVyNTQ3MjE4NTU@._V1_SY1000_CR0,0,674,1000_AL_.jpg"));
        shows.add(new TvShow(
                null,
                "show2",
                "beschrijving 2",
                "2017",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNjRhMGI3ZGItZTMzMS00NzdmLWI1MzMtNjk0ZmY5ZjMyZDdkL2ltYWdlXkEyXkFqcGdeQXVyNTQ3MjE4NTU@._V1_SY1000_CR0,0,674,1000_AL_.jpg"));
        shows.add(new TvShow(
                null,
                "show3",
                "beschrijving 3",
                "2015",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNjRhMGI3ZGItZTMzMS00NzdmLWI1MzMtNjk0ZmY5ZjMyZDdkL2ltYWdlXkEyXkFqcGdeQXVyNTQ3MjE4NTU@._V1_SY1000_CR0,0,674,1000_AL_.jpg"));
        return shows;
    }


}

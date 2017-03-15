package main.service.interfaces;

import main.domain.Movie.BaseMovie;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * Created by ids on 15-3-2017.
 */

public interface IMovieService {
    Iterable<BaseMovie> getAllMovies();
    BaseMovie getMovieById(long id);
}

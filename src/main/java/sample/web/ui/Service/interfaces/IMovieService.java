package sample.web.ui.Service.interfaces;

import sample.web.ui.domain.Movie.BaseMovie;

public interface IMovieService {
    Iterable<BaseMovie> getAllMovies();
    BaseMovie getMovieById(long id);
}

package sample.web.ui.ServiceInterfaces;

import sample.web.ui.domain.Movie.BaseMovie;

public interface IMovieService {
    Iterable<BaseMovie> getAllMovies();
    BaseMovie getMovieById(long id);
}

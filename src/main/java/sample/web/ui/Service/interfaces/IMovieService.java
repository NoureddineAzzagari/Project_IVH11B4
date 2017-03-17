package sample.web.ui.Service.interfaces;

import sample.web.ui.domain.Movie.BaseMovie;

public interface IMovieService {
    Iterable<BaseMovie> getAllMovies();
    BaseMovie getMovieById(long id);
    void addMovie(BaseMovie movie);
    BaseMovie getNewMovie(String title, int year);
    boolean checkForMovies();
}

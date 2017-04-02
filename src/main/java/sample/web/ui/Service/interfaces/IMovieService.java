package sample.web.ui.Service.interfaces;

import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.viewModel.MovieViewModel;

public interface IMovieService {
    MovieViewModel getAllMovies(long userId);
    BaseMovie getMovieById(long id);
    void addMovie(BaseMovie movie);
    BaseMovie getNewMovie(String title, int year);
    boolean checkForMovies();
    Iterable<BaseMovie> searchMovies(String searchString, int searchOption);
}

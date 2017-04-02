package sample.web.ui.viewModel;

import lombok.Getter;
import sample.web.ui.domain.Movie.BaseMovie;

/**
 * Created by Ids van der Zee on 2-4-2017.
 */
@Getter
public class MovieViewModel {
    private Iterable<BaseMovie> movies;
    private Iterable<BaseMovie> favouriteMovies;

    public MovieViewModel(Iterable<BaseMovie> movies, Iterable<BaseMovie> favouriteMovies) {
        this.movies = movies;
        this.favouriteMovies = favouriteMovies;
    }
}

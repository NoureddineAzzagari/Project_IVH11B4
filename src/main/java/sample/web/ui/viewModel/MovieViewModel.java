package sample.web.ui.viewModel;

import lombok.Getter;
import sample.web.ui.domain.Movie.BaseMovie;

/**
 * Created by Ids van der Zee on 2-4-2017.
 */
@Getter
public class MovieViewModel {
    private final Iterable<BaseMovie> movies;
    private final Iterable<BaseMovie> favouriteMovies;

    public MovieViewModel(Iterable<BaseMovie> movies, Iterable<BaseMovie> favouriteMovies) {
        this.movies = movies;
        this.favouriteMovies = favouriteMovies;
    }
}

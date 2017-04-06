package sample.web.ui.viewModel;

import sample.web.ui.domain.tvShow.TvShow;

/**
 * Created by on 4/6/2017.
 */
@lombok.Getter
public class TvShowViewModel {

    private final Iterable<TvShow> tvShows;
    private final Iterable<TvShow> favouriteTvShows;

    public TvShowViewModel(Iterable<TvShow> tvShows, Iterable<TvShow> favouriteTvShows) {
        this.tvShows = tvShows;
        this.favouriteTvShows = tvShows;
    }
}

package sample.web.ui.Service.interfaces;

import sample.web.ui.domain.tvShow.TvShow;

public interface ITvShowService {
    Iterable<TvShow> getAllTvShows();
    TvShow getTvShowById(long id);
}

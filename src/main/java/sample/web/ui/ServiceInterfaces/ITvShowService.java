package sample.web.ui.ServiceInterfaces;

import sample.web.ui.domain.tvShow.TvShow;

public interface ITvShowService {
    Iterable<TvShow> getAllTvShows();
    TvShow getTvShowById(long id);
}

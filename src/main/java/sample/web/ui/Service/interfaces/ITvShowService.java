package sample.web.ui.Service.interfaces;

import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.domain.tvShow.Episode;
import sample.web.ui.domain.tvShow.Season;
import sample.web.ui.domain.tvShow.TvShow;
import sample.web.ui.viewModel.TvShowViewModel;

public interface ITvShowService {
    TvShowViewModel getAllTvShows(long userId);

    TvShow getTvShowById(long id);

    Iterable<Season> getAllSeasons();

    Season getSeasonById(long id);

    Iterable<Episode> getAllEpisodes();

    Episode getEpisodeById(long id);

    TvShow getNewTvShow(String title, int year);

    void addTvShow(TvShow tvShow);

    Iterable<TvShow> searchTvShows(String searchString, int searchOption);
}

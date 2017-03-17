package sample.web.ui.Service.interfaces;

import sample.web.ui.domain.tvShow.Episode;
import sample.web.ui.domain.tvShow.Season;
import sample.web.ui.domain.tvShow.TvShow;

public interface ITvShowService {
    Iterable<TvShow> getAllTvShows();
    TvShow getTvShowById(long id);
    Iterable<Season> getAllSeasons();
    Season getSeasonById(long id);
    Iterable<Episode> getAllEpisodes();
    Episode getEpisodeById(long id);

}

package main.service.interfaces;

import main.domain.tvShow.Episode;
import main.domain.tvShow.Season;
import main.domain.tvShow.TvShow;
import org.springframework.stereotype.Service;

/**
 * Created by ids on 15-3-2017.
 */

public interface ITvShowService {
    Iterable<TvShow> getAllTvShows();
    TvShow getTvShowById(long id);
    Iterable<Season> getAllSeasons();
    Season getSeasonById(long seasonId);
    Iterable<Episode> getAllEpisodes();
    Episode getEpisodeById(long episodeId);
}

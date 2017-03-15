package main.service.concrete;

import main.dataAcces.EpisodeRepository;
import main.dataAcces.SeasonRepository;
import main.dataAcces.TvShowRepository;
import main.domain.tvShow.Episode;
import main.domain.tvShow.Season;
import main.domain.tvShow.TvShow;
import main.service.interfaces.ITvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ids on 15-3-2017.
 */
@Service
public class TvShowService implements ITvShowService {

    private TvShowRepository tvShowRepository;
    private SeasonRepository seasonRepository;
    private EpisodeRepository episodeRepository;

    @Autowired
    public TvShowService(TvShowRepository tvShowRepository, SeasonRepository seasonRepository, EpisodeRepository episodeRepository) {
        this.tvShowRepository = tvShowRepository;
        this.seasonRepository = seasonRepository;
        this.episodeRepository = episodeRepository;
    }

    @Override
    public Iterable<TvShow> getAllTvShows() {
        return tvShowRepository.findAll();
    }

    @Override
    public TvShow getTvShowById(long id) {
        return tvShowRepository.findOne(id);
    }

    @Override
    public Iterable<Season> getAllSeasons() {
        return seasonRepository.findAll();
    }

    @Override
    public Season getSeasonById(long seasonId) {
        return seasonRepository.findOne(seasonId);
    }

    @Override
    public Iterable<Episode> getAllEpisodes() {
        return episodeRepository.findAll();
    }

    @Override
    public Episode getEpisodeById(long episodeId) {
        return episodeRepository.findOne(episodeId);
    }
}

package sample.web.ui.Service.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.Service.interfaces.ITvShowService;
import sample.web.ui.dataAcces.EpisodeRepository;
import sample.web.ui.dataAcces.SeasonRepository;
import sample.web.ui.dataAcces.TvShowRepository;
import sample.web.ui.domain.tvShow.Episode;
import sample.web.ui.domain.tvShow.Season;
import sample.web.ui.domain.tvShow.TvShow;

@Service
public class TvShowService implements ITvShowService {

    private final TvShowRepository tvShowRepository;
    private final SeasonRepository seasonRepository;
    private final EpisodeRepository episodeRepository;

    @Autowired
    public TvShowService(TvShowRepository tvShowRepository, SeasonRepository seasonRepository, EpisodeRepository episodeRepository) {
        this.tvShowRepository = tvShowRepository;
        this.seasonRepository = seasonRepository;
        this.episodeRepository = episodeRepository;
    }

    /**
     * haalt alle tv shows op uit de db
     * @return lijst met tv shows
     */
    public Iterable<TvShow> getAllTvShows(){
        return tvShowRepository.findAll();
    }

    /**
     * haalt een tv show op aan de hand van een id
     * @param id id van de op te halen tv show
     * @return een tv show
     */
    @Override
    public TvShow getTvShowById(long id) {
        return tvShowRepository.findOne(id);
    }

    /**
     * haalt alle seasons op uit de db
     * @return lijst met seasons
     */
    @Override
    public Iterable<Season> getAllSeasons() {
        return seasonRepository.findAll();
    }

    /**
     * haalt een season op aan de hand van een id
     * @param id id van de op te halen season
     * @return een season
     */
    @Override
    public Season getSeasonById(long id) {
        return seasonRepository.findOne(id);
    }

    /**
     * haalt alle afleveringen op
     * @return lijst met afleveringen
     */
    @Override
    public Iterable<Episode> getAllEpisodes() {
        return episodeRepository.findAll();
    }

    /**
     * haalt een aflevering op aan de hand van een id
     * @param id id van de op te halen aflevering
     * @return een aflevering
     */
    @Override
    public Episode getEpisodeById(long id) {
        return episodeRepository.findOne(id);
    }
}

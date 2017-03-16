package sample.web.ui.Service.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.Service.interfaces.ITvShowService;
import sample.web.ui.dataAcces.TvShowRepository;
import sample.web.ui.domain.tvShow.TvShow;

@Service
public class TvShowService implements ITvShowService {

    private final TvShowRepository tvShowRepository;

    @Autowired
    public TvShowService(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    public Iterable<TvShow> getAllTvShows(){
        return tvShowRepository.findAll();
    }
}

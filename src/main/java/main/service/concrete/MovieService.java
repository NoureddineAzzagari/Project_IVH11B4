package main.service.concrete;

import main.dataAcces.BaseMovieRepository;
import main.domain.Movie.BaseMovie;
import main.service.interfaces.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ids on 15-3-2017.
 */
@Service
public class MovieService implements IMovieService {

    private BaseMovieRepository baseMovieRepository;

    @Autowired
    public MovieService(BaseMovieRepository baseMovieRepository) {
        this.baseMovieRepository = baseMovieRepository;
    }

    @Override
    public Iterable<BaseMovie> getAllMovies() {
        return baseMovieRepository.findAll();
    }

    @Override
    public BaseMovie getMovieById(long id) {
        return baseMovieRepository.findOne(id);
    }
}

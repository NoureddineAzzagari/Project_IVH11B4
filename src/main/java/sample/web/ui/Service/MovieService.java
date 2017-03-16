package sample.web.ui.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.ServiceInterfaces.IMovieService;
import sample.web.ui.dataAcces.BaseMovieRepository;
import sample.web.ui.domain.Movie.BaseMovie;

@Service
public class MovieService implements IMovieService {

    private final BaseMovieRepository baseMovieRepository;

    @Autowired
    public MovieService(BaseMovieRepository baseMovieRepository) {
        this.baseMovieRepository = baseMovieRepository;
    }

    public Iterable<BaseMovie> getAllMovies(){
        return baseMovieRepository.findAll();
    }

    public BaseMovie getMovieById(long id){
        return baseMovieRepository.findOne(id);
    }
}

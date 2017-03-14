package main.dataAcces;

import main.domain.Movie.BaseMovie;
import org.springframework.data.repository.CrudRepository;


public interface BaseMovieRepository extends CrudRepository<BaseMovie, Long> {
}

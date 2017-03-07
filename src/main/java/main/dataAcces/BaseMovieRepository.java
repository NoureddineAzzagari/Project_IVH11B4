package main.dataAcces;

import main.domain.BaseMovie;
import org.springframework.data.repository.CrudRepository;


public interface BaseMovieRepository extends CrudRepository<BaseMovie, Long> {
}

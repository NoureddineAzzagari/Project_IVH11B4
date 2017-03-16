package sample.web.ui.dataAcces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.web.ui.domain.Movie.BaseMovie;

@Repository
public interface BaseMovieRepository extends CrudRepository<BaseMovie, Long> {
}

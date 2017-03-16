package sample.web.ui.dataAcces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.web.ui.domain.tvShow.TvShow;

@Repository
public interface TvShowRepository extends CrudRepository<TvShow, Long> {
}

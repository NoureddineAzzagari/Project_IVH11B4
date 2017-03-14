package main.dataAcces;

import main.domain.tvShow.TvShow;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ids on 9-3-2017.
 */
public interface TvShowRepository extends CrudRepository<TvShow, Long> {
}

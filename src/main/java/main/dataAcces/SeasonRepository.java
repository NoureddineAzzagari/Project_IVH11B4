package main.dataAcces;

import main.domain.tvShow.Season;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ids on 9-3-2017.
 */
public interface SeasonRepository extends CrudRepository<Season, Long> {
}

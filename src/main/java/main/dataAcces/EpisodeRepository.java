package main.dataAcces;

import main.domain.tvShow.Episode;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ids on 9-3-2017.
 */
public interface EpisodeRepository extends CrudRepository<Episode, Long> {
}

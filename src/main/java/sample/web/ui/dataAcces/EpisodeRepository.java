package sample.web.ui.dataAcces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.web.ui.domain.tvShow.Episode;

@Repository
public interface EpisodeRepository extends CrudRepository<Episode, Long> {
}

package sample.web.ui.dataAcces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.web.ui.domain.tvShow.Season;

@Repository
public interface SeasonRepository extends CrudRepository<Season, Long> {
}

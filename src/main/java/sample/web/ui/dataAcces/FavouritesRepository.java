package sample.web.ui.dataAcces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.web.ui.domain.Favourites;

@Repository
public interface FavouritesRepository extends CrudRepository<Favourites, Long> {
}

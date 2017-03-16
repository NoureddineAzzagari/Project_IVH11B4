package main.dataAcces;

import main.domain.Favourites;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ids van der Zee on 7-3-2017.
 */
public interface FavouritesRepository extends CrudRepository<Favourites, Long> {
}

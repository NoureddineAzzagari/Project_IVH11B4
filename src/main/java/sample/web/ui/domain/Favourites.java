package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.domain.tvShow.TvShow;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
public class Favourites {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private List<BaseMovie> movies;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private List<TvShow> shows;

    public Favourites(List<BaseMovie> movies, List<TvShow> shows) {
        this.movies = movies;
        this.shows = shows;
    }
}

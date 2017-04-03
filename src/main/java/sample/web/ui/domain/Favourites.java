package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.domain.tvShow.TvShow;
import javax.persistence.*;
import java.util.List;
import java.util.NoSuchElementException;


@Entity
@Getter
@NoArgsConstructor
public class Favourites {

    @Id
    @GeneratedValue
    private long id;

    @ElementCollection(targetClass = BaseMovie.class)
    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private List<BaseMovie> movies;

    @ElementCollection(targetClass = TvShow.class)
    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private List<TvShow> shows;

    public Favourites(List<BaseMovie> movies, List<TvShow> shows) {
        this.movies = movies;
        this.shows = shows;
    }

    public MovieIterator createMovieIterator(){
        return new MovieIterator(this);
    }

    public class MovieIterator{
        private final Favourites favourites;
        private java.util.Iterator iterator;
        private int current;

        public MovieIterator(Favourites favourites) {
            this.favourites = favourites;
        }

        public void first(){
            iterator = favourites.movies.iterator();
            next();
        }

        public void next(){
            try{
                iterator.next();
            }
            catch (NoSuchElementException e){
                current = -999;
            }
        }

        public boolean isDone(){
            return current == -999;
        }

        public int currentItem(){
            return current;
        }
    }
}

package main.domain;

import lombok.Getter;
import main.domain.Movie.BaseMovie;
import main.domain.tvShow.TvShow;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.ArrayList;


@Entity
@Getter
public class Favourites {
    @OneToOne
    private ArrayList<BaseMovie> movies;
    @OneToOne
    private ArrayList<TvShow> shows;
}

package main.domain;

import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import java.util.ArrayList;

@Entity
@Getter
public class TvShow {

    @GeneratedValue
    private long id;
    @OneToOne
    private ArrayList<Season> seasons;
    private String name;
    private String description;
    private String releaseDate;
    private String imageUrl;

    public TvShow(ArrayList<Season> seasons, String name, String description, String releaseDate, String imageUrl) {
        this.seasons = seasons;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
    }
}

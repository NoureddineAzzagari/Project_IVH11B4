package main.domain.tvShow;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class TvShow {

    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    private List<Season> seasons;
    private String name;
    private String description;
    private String releaseDate;
    private String imageUrl;

    public TvShow(List<Season> seasons, String name, String description, String releaseDate, String imageUrl) {
        this.seasons = seasons;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
    }
}

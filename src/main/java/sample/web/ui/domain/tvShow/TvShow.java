package sample.web.ui.domain.tvShow;

import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
public class TvShow {

    @GeneratedValue
    @Id
    private long id;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private final List<Season> seasons;

    private final String name;
    private final String description;
    private final String releaseDate;
    private final String imageUrl;

    public TvShow(List<Season> seasons, String name, String description, String releaseDate, String imageUrl) {
        this.seasons = seasons;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
    }
}

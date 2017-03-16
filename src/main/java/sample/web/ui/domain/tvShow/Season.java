package sample.web.ui.domain.tvShow;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Season {

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private List<Episode> episodes;

    @GeneratedValue
    @Id
    private long id;

    public Season(List<Episode> episodes) {
        this.episodes = episodes;
    }
}

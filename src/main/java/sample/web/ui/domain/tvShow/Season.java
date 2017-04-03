package sample.web.ui.domain.tvShow;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
public class Season {

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private final List<Episode> episodes;

    @GeneratedValue
    @Id
    private long id;

    public Season(List<Episode> episodes) {
        this.episodes = episodes;
    }
}

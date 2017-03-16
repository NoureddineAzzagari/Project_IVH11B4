package sample.web.ui.domain.tvShow;

import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Episode {

    @GeneratedValue
    @Id
    private long id;
    private String name;
    private int time;

    public Episode(String name, int time) {
        this.name = name;
        this.time = time;
    }
}

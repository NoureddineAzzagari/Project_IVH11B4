package main.domain.tvShow;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Season {

    @OneToMany
    private List<Episode> episodes;

    @Id
    @GeneratedValue
    private long id;

    public Season(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }
}

package main.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import java.util.ArrayList;

@Entity
@Getter
public class Season {
    @OneToOne
    private ArrayList<Episode> episodes;
    @GeneratedValue
    private long id;

    public Season(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }
}

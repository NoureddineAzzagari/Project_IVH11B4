package main.domain;

import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Getter
@Entity
public class Episode {

    @GeneratedValue
    private int id;
    private String name;
    private int time;

    public Episode(String name, int time) {
        this.name = name;
        this.time = time;
    }
}

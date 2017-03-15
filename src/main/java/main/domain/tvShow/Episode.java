package main.domain.tvShow;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Episode {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int time;

    public Episode(String name, int time) {
        this.name = name;
        this.time = time;
    }
}

package main.domain;

import lombok.*;
import javax.persistence.*;

/**
 * Created by Ids van der Zee on 23-2-2017.
 */

@Getter
@Entity
@Setter
public class MovieOptions extends DecoratedMovie {

    private String name;
    private int runningTime;

    public MovieOptions(BaseMovie movie, String name, int runningTime){
        super(movie);
        this.name = name;
        this.runningTime = runningTime;
    }

    @Override
    public int runningTime() {
        return movie.runningTime() + this.runningTime;
    }
}

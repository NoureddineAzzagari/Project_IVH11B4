package main.domain.Movie;

import lombok.*;
import javax.persistence.*;

@Getter
@Entity
@Setter
@NoArgsConstructor
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

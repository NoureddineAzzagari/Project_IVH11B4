package main.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class DecoratedMovie extends BaseMovie {

    @OneToOne
    protected BaseMovie movie;

    protected DecoratedMovie(BaseMovie movie){
        this.movie = movie;
    }
}

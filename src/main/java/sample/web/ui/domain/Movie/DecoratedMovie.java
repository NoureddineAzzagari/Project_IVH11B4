package sample.web.ui.domain.Movie;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
abstract class DecoratedMovie extends BaseMovie {

    @OneToOne
    protected BaseMovie movie;

    DecoratedMovie(BaseMovie movie){
        this.movie = movie;
    }
}

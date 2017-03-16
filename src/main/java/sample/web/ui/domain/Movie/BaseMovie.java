package sample.web.ui.domain.Movie;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseMovie {

    @Id
    @GeneratedValue
    private long id;

    public abstract int runningTime();
}

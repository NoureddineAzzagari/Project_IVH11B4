package main.domain;

import lombok.*;
import javax.persistence.*;

/**
 * Created by Ids van der Zee on 23-2-2017.
 */

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

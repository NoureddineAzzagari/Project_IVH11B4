package main.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Ids van der Zee on 15-2-2017.
 */


@Getter

public class Greeting {
    private long id;
    private String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
}

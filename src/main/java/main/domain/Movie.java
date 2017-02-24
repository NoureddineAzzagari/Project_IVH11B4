package main.domain;

import lombok.*;
import javax.persistence.*;

/**
 * Created by Ids van der Zee on 15-2-2017.
 */


@Getter
@Entity
@NoArgsConstructor
@Builder
public class Movie extends BaseMovie {

    private String content;
    private String title;
    private String imgUrl;
    private int runningTime;

    /**
     * @param title
     * @param content
     * @param imgUrl
     * @param runnningTime
     */
    public Movie(String title, String content, String imgUrl, int runnningTime) {

        this.content = content;
        this.imgUrl = imgUrl;
        this.title = title;
        this.runningTime = runnningTime;
    }

    @Override
    public int runningTime() {
        return this.runningTime;
    }
}

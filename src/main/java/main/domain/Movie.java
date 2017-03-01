package main.domain;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

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
    private String releaseDate;

    /**
     * @param title
     * @param content
     * @param imgUrl
     * @param runnningTime
     */
    public Movie(String title, String content, String imgUrl, int runnningTime, String releaseDate) {

        this.content = content;
        this.imgUrl = imgUrl;
        this.title = title;
        this.runningTime = runnningTime;
        this.releaseDate = releaseDate;
    }

    @Override
    public int runningTime() {
        return this.runningTime;
    }
}

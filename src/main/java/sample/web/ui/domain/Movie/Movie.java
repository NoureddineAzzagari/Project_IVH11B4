package sample.web.ui.domain.Movie;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Movie extends BaseMovie {

    private String content;
    private String title;
    private String imgUrl;
    private int runningTime;
    private String releaseDate;

    /**
     * @param title title
     * @param content description
     * @param imgUrl image url
     * @param runningTime running time
     */
    public Movie(String title, String content, String imgUrl, int runningTime, String releaseDate) {

        this.content = content;
        this.imgUrl = imgUrl;
        this.title = title;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
    }

    @Override
    public int runningTime() {
        return this.runningTime;
    }
}

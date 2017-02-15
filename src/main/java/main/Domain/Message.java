package main.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ids on 14-2-2017.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Message is required.")
    private String text;

    @NotEmpty(message = "Summary is required.")
    private String summary;

}

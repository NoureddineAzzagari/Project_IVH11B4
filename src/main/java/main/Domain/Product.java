package main.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ids on 8-2-2017.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    public Product(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public Product(Product p) {
        this.id = p.getId();
        this.name = p.getName();
    }

    @Id
    @GeneratedValue
    private long id;

    private String name;


}

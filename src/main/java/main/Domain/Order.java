package main.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by ids on 8-2-2017.
 */

@Entity
@Table(name="Orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private long id;

    //@OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private ArrayList<Product> products;

    public void add(Product product){
        products.add(product);
    }
}

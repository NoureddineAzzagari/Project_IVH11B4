package main.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;

/**
 * Created by ids on 8-2-2017.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductCatalog {

    @Id
    @GeneratedValue
    private long id;

    //@OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private ArrayList<Product> products;

    public void addProduct(Product product){
        products.add(product);
    }

    public Product find(long id){
        for(Product p : products){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public void add(Product product){
        products.add(product);
    }
}

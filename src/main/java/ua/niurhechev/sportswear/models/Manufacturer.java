package ua.niurhechev.sportswear.models;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "manufacturers")
public class Manufacturer {
    @Id
    @Column(name = "manufacturerId")
    private int manufacturerId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "manufacturer")
    private Set<Product> products = new LinkedHashSet<>();
}

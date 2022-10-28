package ua.niurhechev.sportswear.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "productId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @ManyToOne
    @JoinColumn(name = "manufacturerId")
    private Manufacturer manufacturer;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "size")
    private int size;
}

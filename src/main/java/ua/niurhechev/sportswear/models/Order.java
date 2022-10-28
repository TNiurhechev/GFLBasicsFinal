package ua.niurhechev.sportswear.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column(name = "product")
    private String product;
    @Column(name = "customer")
    private String customer;
    @Column(name = "address")
    private String address;
}

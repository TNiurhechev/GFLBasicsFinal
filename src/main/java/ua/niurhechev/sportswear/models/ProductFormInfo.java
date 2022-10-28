package ua.niurhechev.sportswear.models;

import lombok.Data;

@Data
public class ProductFormInfo {
    private int productId;
    private int manufacturerId;
    private String name;
    private int size;
    private int price;
}

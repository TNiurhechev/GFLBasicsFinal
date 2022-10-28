package ua.niurhechev.sportswear.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.niurhechev.sportswear.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product findByName(String name);
}

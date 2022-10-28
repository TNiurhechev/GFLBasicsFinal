package ua.niurhechev.sportswear.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.niurhechev.sportswear.models.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> findByCustomer(String name);
}

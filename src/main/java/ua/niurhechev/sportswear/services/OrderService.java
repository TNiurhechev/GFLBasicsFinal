package ua.niurhechev.sportswear.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.niurhechev.sportswear.models.Order;
import ua.niurhechev.sportswear.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getByCustomer(String name) {
        return orderRepository.findByCustomer(name);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void delete(int id) {
        orderRepository.deleteById(id);
    }
}

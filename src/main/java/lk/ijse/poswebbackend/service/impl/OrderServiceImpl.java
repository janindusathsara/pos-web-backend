package lk.ijse.poswebbackend.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.ijse.poswebbackend.dto.OrderDto;
import lk.ijse.poswebbackend.entity.Order;
import lk.ijse.poswebbackend.entity.Product;
import lk.ijse.poswebbackend.repository.OrderRepository;
import lk.ijse.poswebbackend.repository.ProductRepository;
import lk.ijse.poswebbackend.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order creatOrder(OrderDto orderDto) {

        Order order = new Order();

        List<Long> products = orderDto.getProducts();
        Set<Product> productsSet = new HashSet<>();
        order.setTotal(0.0);

        for (Long productId : products) {
            Product product = productRepository.findById(productId).orElse(null);

            if (product != null && product.getQty() != 0) {
                productsSet.add(product);
                order.setTotal(order.getTotal() + product.getPrice());
                Integer qty = product.getQty();
                product.setQty(qty-1);
            }
        }

        order.setOrderTime(LocalDateTime.now());
        order.setProducts(productsSet);

        return orderRepository.save(order);

    }
    
}

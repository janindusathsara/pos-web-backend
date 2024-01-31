package lk.ijse.poswebbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.poswebbackend.dto.OrderDto;
import lk.ijse.poswebbackend.entity.Order;

@Service
public interface OrderService {

    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order creatOrder(OrderDto orderDto, Long id);
    List<Order> getOrderByUser(Long id);
    
}

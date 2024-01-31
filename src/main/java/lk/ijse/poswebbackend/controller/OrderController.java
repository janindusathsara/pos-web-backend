package lk.ijse.poswebbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.poswebbackend.dto.OrderDto;
import lk.ijse.poswebbackend.entity.Order;
import lk.ijse.poswebbackend.service.OrderService;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders () {
        List<Order> orderList = orderService.getAllOrders();
        if (orderList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(orderList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById (@PathVariable Long id) {
        Order order = orderService.getOrderById(id);

        if (order != null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PostMapping("/orders/{id}")
    public ResponseEntity<Order> createOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        return ResponseEntity.status(201).body(orderService.creatOrder(orderDto, id));
    }

    @GetMapping("/orders/user/{id}")
    public ResponseEntity<List<Order>> getOrderByUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.getOrderByUser(id));
    }
    
    
}

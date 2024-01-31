package lk.ijse.poswebbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.ijse.poswebbackend.entity.Order;
import lk.ijse.poswebbackend.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user = :user")
    List<Order> findOrderByUser(@Param("user") User user);
    
}

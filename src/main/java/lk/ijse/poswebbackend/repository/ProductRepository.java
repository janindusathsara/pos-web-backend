package lk.ijse.poswebbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.ijse.poswebbackend.entity.Category;
import lk.ijse.poswebbackend.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findProductByCategory(@Param("category") Category category);
    
}

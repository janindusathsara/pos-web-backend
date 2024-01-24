package lk.ijse.poswebbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.ijse.poswebbackend.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}

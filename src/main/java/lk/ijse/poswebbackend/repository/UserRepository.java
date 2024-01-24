package lk.ijse.poswebbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.ijse.poswebbackend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}

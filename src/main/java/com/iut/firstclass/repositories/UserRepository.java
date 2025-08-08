package com.iut.firstclass.repositories;

import com.iut.firstclass.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
}

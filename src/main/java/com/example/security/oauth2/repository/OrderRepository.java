package com.example.security.oauth2.repository;

import com.example.security.oauth2.entity.Order;
import com.example.security.oauth2.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByOwnerOrderByCreatedAtDesc(User owner);
}

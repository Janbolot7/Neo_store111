package com.example.Neo_store111.repository;

import com.example.Neo_store111.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    void deleteOrderByOrderId(Long id);
}
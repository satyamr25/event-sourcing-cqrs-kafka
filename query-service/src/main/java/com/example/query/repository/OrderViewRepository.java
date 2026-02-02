package com.example.query.repository;

import com.example.query.model.OrderView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderViewRepository extends JpaRepository<OrderView, String> {
}

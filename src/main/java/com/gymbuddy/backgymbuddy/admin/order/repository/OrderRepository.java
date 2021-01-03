package com.gymbuddy.backgymbuddy.admin.order.repository;

import com.gymbuddy.backgymbuddy.admin.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

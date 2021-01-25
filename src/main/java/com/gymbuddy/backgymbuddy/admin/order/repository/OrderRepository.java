package com.gymbuddy.backgymbuddy.admin.order.repository;

import com.gymbuddy.backgymbuddy.admin.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}

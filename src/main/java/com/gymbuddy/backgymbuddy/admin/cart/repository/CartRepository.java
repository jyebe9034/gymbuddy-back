package com.gymbuddy.backgymbuddy.admin.cart.repository;

import com.gymbuddy.backgymbuddy.admin.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

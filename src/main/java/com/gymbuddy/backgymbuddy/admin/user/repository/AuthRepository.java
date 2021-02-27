package com.gymbuddy.backgymbuddy.admin.user.repository;

import com.gymbuddy.backgymbuddy.admin.user.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findByEmail(String email);
}

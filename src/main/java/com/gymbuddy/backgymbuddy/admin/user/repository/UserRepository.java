package com.gymbuddy.backgymbuddy.admin.user.repository;

import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUserId(String username);

    Optional<User> findByEmail(String username);
}

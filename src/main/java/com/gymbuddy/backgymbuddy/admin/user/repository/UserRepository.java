package com.gymbuddy.backgymbuddy.admin.user.repository;

import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

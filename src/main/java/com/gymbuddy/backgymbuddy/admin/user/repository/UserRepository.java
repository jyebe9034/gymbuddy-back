package com.gymbuddy.backgymbuddy.admin.user.repository;

import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdentity(String username);

    Optional<User> findByEmail(String email);

    List<User> findAllByGrade(String grade, Pageable pageable);
}

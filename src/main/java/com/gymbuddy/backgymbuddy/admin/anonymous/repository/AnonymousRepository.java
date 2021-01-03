package com.gymbuddy.backgymbuddy.admin.anonymous.repository;

import com.gymbuddy.backgymbuddy.admin.anonymous.domain.Anonymous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousRepository extends JpaRepository<Anonymous, Long> {
}

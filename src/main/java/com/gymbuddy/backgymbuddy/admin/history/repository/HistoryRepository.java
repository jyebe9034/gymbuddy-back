package com.gymbuddy.backgymbuddy.admin.history.repository;

import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}

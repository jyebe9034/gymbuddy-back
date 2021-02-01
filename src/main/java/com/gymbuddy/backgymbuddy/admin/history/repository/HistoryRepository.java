package com.gymbuddy.backgymbuddy.admin.history.repository;

import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "SELECT h FROM History h ORDER BY h.historyDate")
    List<History> findAllByDate();
}

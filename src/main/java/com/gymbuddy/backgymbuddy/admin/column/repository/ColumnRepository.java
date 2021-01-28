package com.gymbuddy.backgymbuddy.admin.column.repository;

import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnRepository extends JpaRepository<Columns, Long> {

    Long deleteByIdIn(List<Long> ids);
}

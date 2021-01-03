package com.gymbuddy.backgymbuddy.admin.columnWriter.repository;

import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CWRepository extends JpaRepository<ColumnWriter, Long> {
}

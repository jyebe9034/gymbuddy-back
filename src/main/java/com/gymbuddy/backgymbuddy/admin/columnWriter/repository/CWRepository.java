package com.gymbuddy.backgymbuddy.admin.columnWriter.repository;

import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CWRepository extends JpaRepository<ColumnWriter, Long> {

    Long deleteByIdIn(List<Long> ids);
}

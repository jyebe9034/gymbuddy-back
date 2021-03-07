package com.gymbuddy.backgymbuddy.admin.program.repository;

import com.gymbuddy.backgymbuddy.admin.program.domain.ProgramOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProgramOptionRepository extends JpaRepository<ProgramOption, Long> {

    @Modifying
    @Transactional
    @Query("delete from ProgramOption o where o.program.id = :id")
    void deleteByProgramId(@Param("id") Long programId);

    List<ProgramOption> findAllByProgramId(Long id);
}

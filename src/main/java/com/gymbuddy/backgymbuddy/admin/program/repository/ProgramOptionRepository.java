package com.gymbuddy.backgymbuddy.admin.program.repository;

import com.gymbuddy.backgymbuddy.admin.program.domain.ProgramOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramOptionRepository extends JpaRepository<ProgramOption, Long> {

    void deleteByProgramId(Long id);

    List<ProgramOption> findAllByProgramId(Long id);
}

package com.gymbuddy.backgymbuddy.admin.program.repository;

import com.gymbuddy.backgymbuddy.admin.program.domain.ProgramOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramOptionRepository extends JpaRepository<ProgramOption, Long> {

    void deleteByProgramId(long id);
}

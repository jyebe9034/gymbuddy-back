package com.gymbuddy.backgymbuddy.admin.program.repository;

import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}

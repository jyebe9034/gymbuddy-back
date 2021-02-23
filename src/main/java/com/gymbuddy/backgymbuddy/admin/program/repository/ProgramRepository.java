package com.gymbuddy.backgymbuddy.admin.program.repository;

import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    List<Program> findAllByMainYn(String mainYn);
}

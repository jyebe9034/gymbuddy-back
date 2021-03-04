package com.gymbuddy.backgymbuddy.admin.program.repository;

import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    List<Program> findAllByMainYn(String mainYn);

    @Query("select p from Program p order by p.mainYn desc, p.id desc")
    Page<Program> findAllByMainYnAndCreateDate(Pageable pageable);

    @Query("select count(p) from Program p where p.mainYn = 'Y'")
    int mainYnCount();
}

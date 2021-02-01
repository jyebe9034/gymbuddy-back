package com.gymbuddy.backgymbuddy.admin.term.repository;

import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {

    Term findByTitle(String title);
}

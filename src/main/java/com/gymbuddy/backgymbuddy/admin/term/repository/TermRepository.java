package com.gymbuddy.backgymbuddy.admin.term.repository;

import com.gymbuddy.backgymbuddy.admin.base.WebMobile;
import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {
    Term findWeb(Long id, WebMobile webMobile);
    Term findMobile(Long id, WebMobile webMobile);
    Long deleteByIds(List<Long> ids);
}

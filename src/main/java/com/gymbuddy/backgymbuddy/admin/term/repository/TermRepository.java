package com.gymbuddy.backgymbuddy.admin.term.repository;

import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findByTitle(String title);

    Term findByTitleAndWebMobile(String title, WebMobileStatus webMobile);
}

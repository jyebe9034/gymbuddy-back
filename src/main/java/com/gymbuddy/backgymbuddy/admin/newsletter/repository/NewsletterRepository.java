package com.gymbuddy.backgymbuddy.admin.newsletter.repository;

import com.gymbuddy.backgymbuddy.admin.newsletter.domain.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {

    List<Newsletter> findAllByCreateDateBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}

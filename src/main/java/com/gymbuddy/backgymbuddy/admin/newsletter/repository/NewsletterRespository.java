package com.gymbuddy.backgymbuddy.admin.newsletter.repository;

import com.gymbuddy.backgymbuddy.admin.newsletter.domain.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsletterRespository extends JpaRepository<Newsletter, Long> {
}

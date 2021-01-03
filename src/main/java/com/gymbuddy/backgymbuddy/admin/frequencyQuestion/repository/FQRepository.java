package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.repository;

import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FreqeuncyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FQRepository extends JpaRepository<FreqeuncyQuestion, Long> {
}

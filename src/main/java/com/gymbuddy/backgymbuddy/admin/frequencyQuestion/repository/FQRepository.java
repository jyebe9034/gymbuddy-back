package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.repository;

import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FrequencyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FQRepository extends JpaRepository<FrequencyQuestion, Long> {

}

package com.gymbuddy.backgymbuddy.admin.question.repository;

import com.gymbuddy.backgymbuddy.admin.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}

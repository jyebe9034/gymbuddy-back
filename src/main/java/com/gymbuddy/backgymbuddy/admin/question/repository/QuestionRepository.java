package com.gymbuddy.backgymbuddy.admin.question.repository;

import com.gymbuddy.backgymbuddy.admin.question.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findQuestionListByCreateId(String create_id, Pageable pageable);
}

package com.gymbuddy.backgymbuddy.admin.question.repository;

import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {

    List<QuestionComment> findQuestionCommentsById(Long id);
}

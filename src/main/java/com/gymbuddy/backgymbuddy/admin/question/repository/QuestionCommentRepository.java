package com.gymbuddy.backgymbuddy.admin.question.repository;

import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {

    @Query("select c from QuestionComment c where c.question.id = :id")
    List<QuestionComment> findByQuestionId(@Param("id") Long id);

    @Query("select count(c) from QuestionComment c where c.question.id = :id")
    int commentCount(@Param("id") Long id);
}

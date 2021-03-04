package com.gymbuddy.backgymbuddy.admin.question.repository;

import com.gymbuddy.backgymbuddy.admin.enums.category.QuestionEnum;
import com.gymbuddy.backgymbuddy.admin.question.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findQuestionListByCreateId(@Param("createId") String createId, Pageable pageable);

    Page<Question> findAllByCategoryIdAndTitleContaining(@Param("categoryId") QuestionEnum categoryId, @Param("title") String title, Pageable pageable);
    Page<Question> findAllByCategoryIdAndCreateIdContaining(@Param("categoryId") QuestionEnum categoryId, @Param("createId") String createId, Pageable pageable);

    @Query("select count(q) from Question q")
    int commentCount();
}

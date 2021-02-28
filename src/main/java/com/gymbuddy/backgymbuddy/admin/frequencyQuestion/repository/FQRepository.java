package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.repository;

import com.gymbuddy.backgymbuddy.admin.enums.category.FaqEnum;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FrequencyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FQRepository extends JpaRepository<FrequencyQuestion, Long> {

    List<FrequencyQuestion> findFrequencyQuestionByCategoryId(@Param("categoryId") FaqEnum categoryId);
}

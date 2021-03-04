package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.repository;

import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FrequencyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FQRepository extends JpaRepository<FrequencyQuestion, Long> {

    @Query("select f from FrequencyQuestion f order by f.categoryId")
    List<FrequencyQuestion> findAllByCategoryId();
}

package com.gymbuddy.backgymbuddy.admin.news.repository;

import com.gymbuddy.backgymbuddy.admin.news.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

}

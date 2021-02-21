package com.gymbuddy.backgymbuddy.admin.youtube.repository;

import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YoutubeRepository extends JpaRepository<Youtube, Long> {

    List<Youtube> findTop9ByOrderByIdDesc();

}

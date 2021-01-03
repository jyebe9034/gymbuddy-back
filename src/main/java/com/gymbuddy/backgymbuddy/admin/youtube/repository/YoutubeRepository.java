package com.gymbuddy.backgymbuddy.admin.youtube.repository;

import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YoutubeRepository extends JpaRepository<Youtube, Long> {
}

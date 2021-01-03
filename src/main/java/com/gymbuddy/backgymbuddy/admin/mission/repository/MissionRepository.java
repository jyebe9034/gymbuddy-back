package com.gymbuddy.backgymbuddy.admin.mission.repository;

import com.gymbuddy.backgymbuddy.admin.mission.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}

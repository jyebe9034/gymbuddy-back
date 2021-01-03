package com.gymbuddy.backgymbuddy.admin.mainBanner.repository;

import com.gymbuddy.backgymbuddy.admin.mainBanner.domain.MainBanner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MBRepository extends JpaRepository<MainBanner, Long> {
}

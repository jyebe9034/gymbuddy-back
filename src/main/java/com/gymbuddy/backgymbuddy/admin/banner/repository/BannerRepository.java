package com.gymbuddy.backgymbuddy.admin.banner.repository;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}

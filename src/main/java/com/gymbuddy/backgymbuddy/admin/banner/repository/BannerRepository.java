package com.gymbuddy.backgymbuddy.admin.banner.repository;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    Long deleteByIdIn(List<Long> ids);
}

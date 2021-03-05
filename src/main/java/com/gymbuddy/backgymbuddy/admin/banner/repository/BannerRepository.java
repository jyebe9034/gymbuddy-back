package com.gymbuddy.backgymbuddy.admin.banner.repository;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    Optional<Banner> findByTitle(String title);
}

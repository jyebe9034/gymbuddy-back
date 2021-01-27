package com.gymbuddy.backgymbuddy.admin.banner.service;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    public List<Banner> findAll() {
        return bannerRepository.findAll();
    }

    public Banner findOne(Long id) {
        return bannerRepository.findById(id).get();
    }

    @Transactional
    public Long save(Banner banner) {
        bannerRepository.save(banner);
        return banner.getId();
    }

    @Transactional
    public void update(Long id, String title, String link) {
        Banner banner = bannerRepository.findById(id).get();
        banner.setTitle(title);
        banner.setLink(link);
    }

    @Transactional
    public int delete(List<Long> ids) {
        Long deletedRows = bannerRepository.deleteByIdIn(ids);
        if (ids.size() == deletedRows.intValue()) {
            return 1;
        }
        return 0;
    }
}

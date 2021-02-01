package com.gymbuddy.backgymbuddy.admin.banner.service;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public void update(Long id, Map<String, Object> param) {
        Banner banner = bannerRepository.findById(id).get();
        if (param.get("title") != null) {
            banner.setTitle(Objects.toString(param.get("title")));
        }
        if (param.get("link") != null) {
            banner.setLink(Objects.toString(param.get("link")));
        }
    }

    @Transactional
    public void delete(List<Integer> ids) {
        for (int id : ids) {
            long idL = new Long(id);
            bannerRepository.deleteById(idL);
        }
    }
}

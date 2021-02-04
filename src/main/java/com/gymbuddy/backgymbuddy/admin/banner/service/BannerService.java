package com.gymbuddy.backgymbuddy.admin.banner.service;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.domain.BannerDto;
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
    public Long save(BannerDto banner) {
        Banner entity = new Banner();
        entity.setTitle(banner.getTitle());
        entity.setCategoryId(banner.getCategoryId());
        entity.setLink(banner.getLink());
        entity.setBtnTitle(banner.getBtnTitle());
        entity.setImgPath(banner.getImgPath());
        entity.setImgName(banner.getImgName());

        bannerRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, BannerDto banner) {
        Banner origin = findOne(id);
        if (banner.getTitle() != null) {
            origin.setTitle(banner.getTitle());
        }
        if (banner.getCategoryId() != null) {
            origin.setCategoryId(banner.getCategoryId());
        }
        if (banner.getLink() != null) {
            origin.setLink(banner.getLink());
        }
        if (banner.getBtnTitle() != null) {
            origin.setBtnTitle(banner.getBtnTitle());
        }
        if (banner.getImgPath() != null) {
            origin.setImgPath(banner.getImgPath());
        }
        if (banner.getImgName() != null) {
            origin.setImgName(banner.getImgName());
        }
    }

    @Transactional
    public void delete(Long id) {
        bannerRepository.deleteById(id);
    }
}

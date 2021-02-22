package com.gymbuddy.backgymbuddy.admin.banner.service;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.domain.BannerDto;
import com.gymbuddy.backgymbuddy.admin.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public Long save(BannerDto banner) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        Banner entity = new Banner();
        if (banner.getTitle() != null) {
            entity.setTitle(banner.getTitle());
        }
        if (banner.getCategoryId() != null) {
            entity.setCategoryId(banner.getCategoryId());
        }
        if (banner.getLink() != null) {
            entity.setLink(banner.getLink());
        }
        if (banner.getBtnTitle() != null) {
            entity.setBtnTitle(banner.getBtnTitle());
        }
        if (banner.getImgPath() != null) {
            entity.setImgPath(banner.getImgPath());
        }
        if (banner.getImgName() != null) {
            entity.setImgName(banner.getImgName());
        }
        entity.setCreateDate(LocalDateTime.now());
//        entity.setCreateId(loginId);
        entity.setUpdateDate(LocalDateTime.now());
//        entity.setUpdateId(loginId);

        bannerRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, BannerDto banner) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        Banner origin = findOne(id);
        if (origin.getTitle() != null && !origin.getTitle().equals(banner.getTitle())) {
            origin.setTitle(banner.getTitle());
        }
        if (origin.getCategoryId() != null && !origin.getCategoryId().equals(banner.getCategoryId())) {
            origin.setCategoryId(banner.getCategoryId());
        }
        if (origin.getLink() != null && !origin.getLink().equals(banner.getLink())) {
            origin.setLink(banner.getLink());
        }
        if (origin.getBtnTitle() != null && !origin.getBtnTitle().equals(banner.getBtnTitle())) {
            origin.setBtnTitle(banner.getBtnTitle());
        }
        if (origin.getImgPath() != null && !origin.getImgPath().equals(banner.getImgPath())) {
            origin.setImgPath(banner.getImgPath());
        }
        if (origin.getImgName() != null && !origin.getImgName().equals(banner.getImgName())) {
            origin.setImgName(banner.getImgName());
        }
        origin.setUpdateDate(LocalDateTime.now());
//        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        bannerRepository.deleteById(id);
    }
}

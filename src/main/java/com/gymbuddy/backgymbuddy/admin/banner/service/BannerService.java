package com.gymbuddy.backgymbuddy.admin.banner.service;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.domain.BannerDto;
import com.gymbuddy.backgymbuddy.admin.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        entity.setTitle(banner.getTitle());
        entity.setCategoryId(banner.getCategoryId());
        entity.setLink(banner.getLink());
        entity.setBtnTitle(banner.getBtnTitle());
        entity.setImgPath(banner.getImgPath());
        entity.setImgName(banner.getImgName());
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
        origin.setUpdateDate(LocalDateTime.now());
//        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        bannerRepository.deleteById(id);
    }
}

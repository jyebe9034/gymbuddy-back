package com.gymbuddy.backgymbuddy.admin.banner.service;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.domain.BannerDto;
import com.gymbuddy.backgymbuddy.admin.banner.repository.BannerRepository;
import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        Optional<Banner> byId = bannerRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 배너입니다.");
        }
        return byId.get();
    }

    @Transactional
    public Long save(BannerDto banner) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        Banner entity = new Banner();
        if (banner.getTitle() != null) {
            Optional<Banner> byTitle = bannerRepository.findByTitle(banner.getTitle());
            if (byTitle.isPresent()) {
                throw new DMException("동일한 제목의 배너가 존재합니다.");
            }
            entity.setTitle(banner.getTitle());
        } else {
            throw new DMException("제목을 입력해주세요.");
        }
        if (banner.getCategoryId() != null) {
            entity.setCategoryId(banner.getCategoryId());
        } else {
            throw new DMException("카테고리를 입력해주세요.");
        }
        if (banner.getLink() != null) {
            entity.setLink(banner.getLink());
        } else {
            throw new DMException("링크를 입력해주세요.");
        }
        if (banner.getBtnTitle() != null) {
            entity.setBtnTitle(banner.getBtnTitle());
        } else {
            throw new DMException("버튼명을 입력해주세요.");
        }
        if (banner.getImgPath() != null) {
            entity.setImgPath(banner.getImgPath());
        } else {
            throw new DMException("이미지를 등록해주세요.");
        }
        if (banner.getImgName() != null) {
            entity.setImgName(banner.getImgName());
        } else {
            throw new DMException("이미지를 등록해주세요.");
        }
        entity.setCreateId("jihyeTest");
        entity.setUpdateId("jihyeTest");

        bannerRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, BannerDto banner) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

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
        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        bannerRepository.deleteById(id);
    }
}

package com.gymbuddy.backgymbuddy.admin.banner.controller;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.service.BannerService;
import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BannerController extends BaseController {

    private final BannerService bannerService;

    /**
     * 메인 배너 조회
     */
    @GetMapping("/mainBannerList")
    public List<Banner> selectMainBannerList() {
        return null;
    }

    /**
     * 메인 베너 상세 조회
     */
    @GetMapping("/mainBannerDetail/{no}")
    public Map<String, Object> selectMainBannerDetail(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 메인 베너 등록
     */
    @PostMapping("/newMainBanner")
    public Map<String, Object> insertMainBanner(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 메인 베너 수정
     */
    @PostMapping("/updateMainBanner")
    public Map<String, Object> updateMainBanner(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 메인 베너 삭제
     */
    @GetMapping("/deleteMainBanner")
    public Map<String, Object> deleteMainBanner(@RequestParam Map<String, Object> param) {
        return null;
    }
}

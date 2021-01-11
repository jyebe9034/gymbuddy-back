package com.gymbuddy.backgymbuddy.admin.mainBanner.controller;

import com.gymbuddy.backgymbuddy.admin.mainBanner.domain.MainBanner;
import com.gymbuddy.backgymbuddy.admin.mainBanner.service.MBService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MBController {

    private final MBService mbService;

    /**
     * 메인 배너 조회
     */
    @GetMapping("/mainBannerList")
    public List<MainBanner> selectMainBannerList() {
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
     * @param no 삭제할 글번호
     */
    @GetMapping("/deleteMainBanner/{no}")
    public Map<String, Object> deleteMainBanner(@PathVariable("no") int no) {
        return null;
    }
}

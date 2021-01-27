package com.gymbuddy.backgymbuddy.admin.banner.controller;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.service.BannerService;
import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.BANNER_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BannerController extends BaseController {

    private final String URI_PREFIX = BANNER_PREFIX;

    private final BannerService bannerService;

    /**
     * 메인 배너 조회
     */
    @GetMapping(URI_PREFIX + "/all")
    public ResponseEntity<List<Banner>> selectMainBannerList() {
        return createResponseEntity(true, bannerService.findAll());
    }

    /**
     * 메인 베너 상세 조회
     */
    @GetMapping(URI_PREFIX + "/detail/{id}")
    public ResponseEntity<Banner> selectMainBannerDetail(@PathVariable("id") Long id) {
        log.info("메인 배너 아이디로 조회: {}", id);
        return createResponseEntity(true, bannerService.findOne(id));
    }

    /**
     * 메인 베너 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertMainBanner(@RequestBody Banner banner) {
        log.info("메인 배너 등록: {}", banner);
        Long id = bannerService.save(banner);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 메인 베너의 제목과 링크 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateMainBanner(@PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("메인 배너 수정 - id: {}, param: {}", id, param);
        String title = Objects.toString(param.get("title"));
        String link = Objects.toString(param.get("link"));
        bannerService.update(id, title, link);
        Banner findBanner = bannerService.findOne(id);

        Map<String, Object> result = new HashMap<>();
        result.put("id", findBanner.getId());
        result.put("title", findBanner.getTitle());
        result.put("link", findBanner.getLink());
        return createResponseEntity(true, result);
    }

    /**
     * 메인 베너 삭제
     */
    @DeleteMapping(URI_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteMainBanner(@RequestParam List<Long> ids) {
        log.info("메인 베너 삭제: {}", ids.toString());
        int deleteResult = bannerService.delete(ids);

        Map<String, Object> result = new HashMap<>();
        result.put("result", deleteResult);
        return createResponseEntity(true, result);
    }
}

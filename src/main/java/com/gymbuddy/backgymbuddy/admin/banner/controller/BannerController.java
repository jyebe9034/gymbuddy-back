package com.gymbuddy.backgymbuddy.admin.banner.controller;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.domain.BannerDto;
import com.gymbuddy.backgymbuddy.admin.banner.service.BannerService;
import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private final HttpSession session;

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
    public ResponseEntity<Map<String, Object>> insertMainBanner(@ModelAttribute BannerDto banner) {
        log.info("메인 배너 등록: {}", banner);

        // 이미지 업로드
        String rootPath = System.getProperty("user.dir") + "/src/main/resources/static/img/banner";
        String filename = banner.getFile().getOriginalFilename();
        try {
            File newFile = new File(rootPath);
            if (!newFile.exists()) {
                newFile.mkdir();
            }
            File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
            banner.getFile().transferTo(realFile);
            banner.setImgName(filename);
            banner.setImgPath("/resources/static/img/banner/" + realFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        // 저장
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
        bannerService.update(id, param);
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
    public ResponseEntity<Map<String, Object>> deleteMainBanner(@RequestBody List<Integer> ids) {
        log.info("메인 베너 삭제: {}", ids.toString());
        bannerService.delete(ids);

        Map<String, Object> result = new HashMap<>();
        result.put("result", 0);
        return createResponseEntity(true, result);
    }
}

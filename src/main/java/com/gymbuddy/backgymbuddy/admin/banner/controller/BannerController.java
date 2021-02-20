package com.gymbuddy.backgymbuddy.admin.banner.controller;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.domain.BannerDto;
import com.gymbuddy.backgymbuddy.admin.banner.service.BannerService;
import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.BANNER_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BannerController extends BaseController {

    private String bannerPath = "/resources/static/img/banner";
    private String rootPath = System.getProperty("user.dir") + "/src/main" + bannerPath;
    private File newFile = new File(rootPath);

    private final BannerService bannerService;

    /**
     * 메인 배너 조회
     */
    @GetMapping(BANNER_PREFIX + "/all")
    public ResponseEntity<List<Banner>> selectMainBannerList() {
        return createResponseEntity(true, bannerService.findAll());
    }

    /**
     * 메인 베너 상세 조회
     */
    @GetMapping(BANNER_PREFIX + "/detail/{id}")
    public ResponseEntity<Banner> selectMainBannerDetail(@PathVariable("id") Long id) {
        log.info("메인 배너 아이디로 조회: {}", id);
        return createResponseEntity(true, bannerService.findOne(id));
    }

    /**
     * 메인 베너 등록
     */
    @PostMapping(BANNER_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertMainBanner(@ModelAttribute BannerDto banner) {
        log.info("메인 배너 등록: {}", banner);

        // 이미지 업로드
        String filename = banner.getFile().getOriginalFilename();
        try {
            if (!newFile.exists()) {
                newFile.mkdir();
            }
            File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
            banner.getFile().transferTo(realFile);
            banner.setImgName(filename);
            banner.setImgPath(bannerPath + "/" + realFile.getName());
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
    @PutMapping(BANNER_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateMainBanner(@PathVariable("id") Long id, @ModelAttribute BannerDto banner) {
        log.info("메인 배너 수정 - id: {}, banner: {}", id, banner);

        if (banner.getFile() != null) {
            Banner origin = bannerService.findOne(id);
            String filename = banner.getFile().getOriginalFilename();
            if (!origin.getImgName().equals(filename)) {
                try {
                    File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
                    banner.getFile().transferTo(realFile);
                    banner.setImgName(filename);
                    banner.setImgPath(bannerPath + "/" + realFile.getName());

                    File originFile = new File(newFile + "/" + origin.getImgPath());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }

        bannerService.update(id, banner);
        Banner findBanner = bannerService.findOne(id);

        boolean flag = true;
        if (banner.getTitle() != null) {
            flag = banner.getTitle().equals(findBanner.getTitle()) ? true : false;
        }
        if (banner.getCategoryId() != null) {
            flag = banner.getCategoryId().equals(findBanner.getCategoryId()) ? true : false;
        }
        if (banner.getLink() != null) {
            flag = banner.getLink().equals(findBanner.getLink()) ? true : false;
        }
        if (banner.getBtnTitle() != null) {
            flag = banner.getBtnTitle().equals(findBanner.getBtnTitle()) ? true : false;
        }
        if (banner.getImgPath() != null) {
            flag = banner.getImgPath().equals(findBanner.getImgPath()) ? true : false;
        }
        if (banner.getImgName() != null) {
            flag = banner.getImgName().equals(findBanner.getImgName()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 메인 베너 삭제
     */
    @DeleteMapping(BANNER_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteMainBanner(@RequestBody List<Integer> ids) {
        log.info("메인 베너 삭제: {}", ids.toString());

        for (int id : ids) {
            long idL = new Long(id);
            Banner origin = bannerService.findOne(idL);
            // 이미지 삭제
            File originFile = new File(newFile + "/" + origin.getImgPath());
            if (originFile.exists()) {
                originFile.delete();
            }
            bannerService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }
}

package com.gymbuddy.backgymbuddy.admin.businessIdentity.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.base.WebMobile;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.service.BIService;
import com.gymbuddy.backgymbuddy.admin.newsletter.domain.Newsletter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.BI_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BIController extends BaseController {

    private final String URI_PREFIX = BI_PREFIX;

    private final BIService biService;

    /**
     * 웹 BI 조회
     */
    @GetMapping(URI_PREFIX + "/WebBi")
    public ResponseEntity<BusinessIdentity> selectWebBi() {
        return createResponseEntity(true, biService.findWeb(WebMobile.WEB));
    }

    /**
     * 웹 BI 등록(이미지 1개)
     */
    @PostMapping(URI_PREFIX + "/newWebBi")
    public ResponseEntity<Map<String, Object>> insertWebBi(@RequestBody BusinessIdentity bi) {
        log.info("웹 BI 등록: {}", bi);
        Long id = biService.save(bi);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 웹 BI 수정
     */
    @PutMapping(URI_PREFIX + "/updateWebBi/{id}")
    public ResponseEntity<Map<String, Object>> updateWebBi(
            @PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("모바일 BI id: {}, param: {}", id, param);
        biService.update(id);

        BusinessIdentity findBi = biService.findOne(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", findBi.getId());
        result.put("imgName", findBi.getImgName());
        result.put("imgPath", findBi.getImgPath());
        return createResponseEntity(true, result);
    }

    /**
     * 웹 BI 삭제
     * @param id 삭제할 글번호
     */
    @DeleteMapping(URI_PREFIX + "/deleteWebBi/{id}")
    public ResponseEntity<BusinessIdentity> deleteWebBi(@PathVariable("id") Long id) {
        log.info("웹 BI 삭제: {}", id);
        return createResponseEntity(true, biService.delete(id));
    }

    /**
     * 모바일 BI 조회
     */
    @GetMapping(URI_PREFIX + "/MobileBi")
    public ResponseEntity<BusinessIdentity> selectMobileBi() {
        return createResponseEntity(true, biService.findMobile(WebMobile.MOBILE));
    }

    /**
     * 모바일 BI 등록(이미지 1개)
     */
    @PostMapping(URI_PREFIX + "/newMobileBi")
    public ResponseEntity<Map<String, Object>> insertMobileBi(@RequestBody BusinessIdentity bi) {
        log.info("모바일 BI 등록: {}", bi);
        Long id = biService.save(bi);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 모바일 BI 수정
     */
    @PutMapping(URI_PREFIX + "/updateMobileBi/{id}")
    public ResponseEntity<Map<String, Object>> updateMobileBi(
            @PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("모바일 BI id: {}, param: {}", id, param);
        biService.update(id);

        BusinessIdentity findBi = biService.findOne(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", findBi.getId());
        result.put("imgName", findBi.getImgName());
        result.put("imgPath", findBi.getImgPath());
        return createResponseEntity(true, result);
    }

    /**
     * 모바일 BI 삭제
     * @param id 삭제할 글번호
     */
    @DeleteMapping(URI_PREFIX + "/deleteMobileBi/{id}")
    public ResponseEntity<Map<String, Object>> deleteMobileBi(@PathVariable("id") Long id) {
        log.info("모바일 BI 삭제: {}", id);
        return createResponseEntity(true, biService.delete(id));
    }

}

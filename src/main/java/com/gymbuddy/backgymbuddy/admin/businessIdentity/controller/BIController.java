package com.gymbuddy.backgymbuddy.admin.businessIdentity.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.service.BIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BIController extends BaseController {

    private final BIService biService;

    /**
     * 웹 BI 조회
     */
    @GetMapping("/WebBi")
    public Map<String, Object> selectWebBi() {
        return null;
    }

    /**
     * 웹 BI 등록(이미지 1개)
     */
    @PostMapping("/newWebBi")
    public Map<String, Object> insertWebBi(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 웹 BI 삭제
     * @param no 삭제할 글번호
     */
    @GetMapping("/deleteWebBi/{no}")
    public Map<String, Object> deleteWebBi(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 모바일 BI 조회
     */
    @GetMapping("/MobildBi")
    public Map<String, Object> selectMobileBi() {
        return null;
    }

    /**
     * 모바일 BI 등록(이미지 1개)
     */
    @PostMapping("/newMobileBi")
    public Map<String, Object> insertMobileBi(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 모바일 BI 삭제
     * @param no 삭제할 글번호
     */
    @GetMapping("/deleteMobileBi/{no}")
    public Map<String, Object> deleteMobileBi(@PathVariable("no") int no) {
        return null;
    }

}

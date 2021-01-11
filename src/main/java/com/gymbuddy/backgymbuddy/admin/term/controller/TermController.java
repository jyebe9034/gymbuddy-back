package com.gymbuddy.backgymbuddy.admin.term.controller;

import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import com.gymbuddy.backgymbuddy.admin.term.service.TermService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TermController {

    private final TermService termService;

    /**
     * 전체 이용약관 조회
     */
    @GetMapping("/allTerm")
    public List<Term> selectTermList() {
        return null;
    }

    /**
     * 이용약관 상세
     */
    @GetMapping("/termDetail/{no}")
    public Map<String, Object> selectTermDetail() {
        return null;
    }

    /**
     * 웹 이용약관 등록
     */
    @PostMapping("/newWebTerm")
    public Map<String, Object> insertWebTerm(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 웹 이용약관 삭제
     */
    @GetMapping("/deleteWebTerm/{no}")
    public Map<String, Object> deleteWebTerm(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 모바 이용약관 등록
     */
    @PostMapping("/newMobileTerm")
    public Map<String, Object> insertMobileTerm(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 모바일 이용약관 삭제
     */
    @GetMapping("/deleteMobileTerm/{no}")
    public Map<String, Object> deleteMobileTerm(@PathVariable("no") int no) {
        return null;
    }
}

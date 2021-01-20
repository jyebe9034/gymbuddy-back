package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.controller;

import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FreqeuncyQuestion;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.service.FQService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FQController {

    private final FQService fqService;

    /**
     * 전체 자주묻는질문 조회
     */
    @GetMapping("/allFq")
    public List<FreqeuncyQuestion> selectFQList() {
        return null;
    }

    /**
     * 자주묻는질문 상세
     */
    @GetMapping("/fqDetail/{no}")
    public Map<String, Object> selectFqDetail(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 자주묻는질문 등록
     */
    @PostMapping("/newFq")
    public Map<String, Object> insertFq(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 자주묻는질문 수정
     */
    @PostMapping("/updateFq")
    public Map<String, Object> updateFq(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 자주묻는질문 삭제
     */
    @GetMapping("/deleteFq")
    public Map<String, Object> deleteFq(@RequestParam Map<String, Object> param) {
        return null;
    }
}

package com.gymbuddy.backgymbuddy.admin.news.controller;

import com.gymbuddy.backgymbuddy.admin.news.domain.News;
import com.gymbuddy.backgymbuddy.admin.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    /**
     * 전체 대외뉴스 조회(관리자)
     */
    @GetMapping("/allNews")
    public List<News> selectNewsList() {
        return null;
    }

    /**
     * 메인 노출 대외뉴스 조회
     */
    @GetMapping("/mainNews")
    public List<News> selectMainNewsList() {
        return null;
    }

    /**
     * 대외뉴스 상세
     */
    @GetMapping("/newsDetail/{no}")
    public Map<String, Object> selectNewsDetail(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 대외뉴스 등록
     */
    @PostMapping("/newNews")
    public Map<String, Object> insertNews(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 대외뉴스 수정
     */
    @PostMapping("/updateNews")
    public Map<String, Object> updateNews(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 대외뉴스 삭제
     */
    @GetMapping("/deleteNews")
    public Map<String, Object> deleteNews(@RequestParam Map<String, Object> param) {
        return null;
    }
}

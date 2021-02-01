package com.gymbuddy.backgymbuddy.admin.news.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.news.domain.News;
import com.gymbuddy.backgymbuddy.admin.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.NEWS_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NewsController extends BaseController {

    private final String URI_PREFIX = NEWS_PREFIX;

    private final NewsService newsService;

    /**
     * 전체 대외뉴스 조회(관리자)
     */
    @GetMapping(URI_PREFIX + "/all/{page}")
    public ResponseEntity<List<News>> selectNewsList(@PathVariable("page") int page) {
        return createResponseEntity(true, newsService.findAll(page));
    }

    /**
     * 메인 노출 대외뉴스 조회(최근 5개만)
     */
    @GetMapping(URI_PREFIX + "/mainAll")
    public ResponseEntity<List<News>> selectMainNewsList() {
        return createResponseEntity(true, newsService.findAllForMain());
    }

    /**
     * 대외뉴스 상세
     */
    @GetMapping(URI_PREFIX + "/detail/{id}")
    public ResponseEntity<News> selectNewsDetail(@PathVariable("id") Long id) {
        log.info("대외뉴스 아이디로 조회: {}", id);
        return createResponseEntity(true, newsService.findOne(id));
    }

    /**
     * 대외뉴스 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertNews(@RequestBody News news) {
        log.info("대외뉴스 등록: {}", news);
        Long id = newsService.save(news);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 대외뉴스 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateNews(@PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("대외뉴스 수정 - id: {}, param: {}", id, param);
        newsService.update(id, param);
        News findNews = newsService.findOne(id);

        Map<String, Object> result = new HashMap<>();
        result.put("id", findNews.getId());
        result.put("title", findNews.getTitle());
        result.put("contents", findNews.getContents());
        return createResponseEntity(true, result);
    }

    // TODO 이미지 추가 및 삭제 로직 필요

    /**
     * 대외뉴스 삭제
     */
    @DeleteMapping(URI_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteNews(@RequestBody List<Integer> ids) {
        log.info("대외뉴스 삭제: {}", ids);
        newsService.delete(ids);

        Map<String, Object> result = new HashMap<>();
        result.put("result", 0);
        return createResponseEntity(true, result);
    }
}

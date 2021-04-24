package com.gymbuddy.backgymbuddy.admin.news.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.news.domain.News;
import com.gymbuddy.backgymbuddy.admin.news.domain.NewsDto;
import com.gymbuddy.backgymbuddy.admin.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_NEWS_PREFIX;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.NEWS_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NewsController extends BaseController {

    private String newsPath = "/resources/images/news";
    private String rootPath = "/home/www" + newsPath;
    private File newFile = new File(rootPath);

    private final NewsService newsService;

    @GetMapping(NEWS_PREFIX + "/totalCount")
    public ResponseEntity<Map<String, Object>> selectNewsTotalCount() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", newsService.selectTotalCount());
        return createResponseEntity(true, result);
    }

    /**
     * 전체 대외뉴스 조회(관리자)
     */
    @GetMapping(NEWS_PREFIX + "/all/{page}")
    public ResponseEntity<List<News>> selectNewsList(@PathVariable("page") int page) {
        return createResponseEntity(true, newsService.findAll(page));
    }

    /**
     * 대외뉴스 상세
     */
    @GetMapping(NEWS_PREFIX + "/detail/{id}")
    public ResponseEntity<News> selectNewsDetail(@PathVariable("id") Long id) {
        log.info("대외뉴스 아이디로 조회: {}", id);
        return createResponseEntity(true, newsService.findOne(id));
    }

    /**
     * 대외뉴스 등록
     */
    @PostMapping(ADMIN_NEWS_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertNews(@ModelAttribute NewsDto news) {
        log.info("대외뉴스 등록: {}", news);

        // 이미지 업로드
        if (news.getFile() != null) {
            String filename = news.getFile().getOriginalFilename();
            try {
                if (!newFile.exists()) {
                    try {
                        newFile.mkdir();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
                File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
                news.getFile().transferTo(realFile);
                news.setImgName(realFile.getName());
                news.setImgPath(newsPath + "/" + realFile.getName());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", newsService.save(news));
        return createResponseEntity(true, result);
    }

    /**
     * 대외뉴스 수정
     */
    @PutMapping(ADMIN_NEWS_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateNews(@PathVariable("id") Long id, @ModelAttribute NewsDto news) {
        log.info("대외뉴스 수정 - id: {}, param: {}", id, news);

        if (news.getFile() != null) {
            String filename = news.getFile().getOriginalFilename();
            // 이미지 업로드
            try {
                File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
                news.getFile().transferTo(realFile);
                news.setImgName(realFile.getName());
                news.setImgPath(newsPath + "/" + realFile.getName());

                // 기존 이미지 파일 서버에서 삭제
                News origin = newsService.findOne(id);
                if (origin.getImgPath() != null) {
                    File originFile = new File(origin.getImgPath());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        newsService.update(id, news);
        News findNews = newsService.findOne(id);

        boolean flag = true;
        if (news.getTitle() != null) {
            flag = news.getTitle().equals(findNews.getTitle()) ? true : false;
        }
        if (news.getContents() != null) {
            flag = news.getContents().equals(findNews.getContents()) ? true : false;
        }
        if (news.getImgPath() != null) {
            flag = news.getImgPath().equals(findNews.getImgPath());
        }
        if (news.getImgName() != null) {
            flag = news.getImgName().equals(findNews.getImgName());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 대외뉴스 삭제
     */
    @DeleteMapping(ADMIN_NEWS_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteNews(@RequestBody List<Integer> ids) {
        log.info("대외뉴스 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            News origin = newsService.findOne(idL);
            // 이미지 삭제
            if (origin.getImgPath() != null) {
                File originFile = new File(origin.getImgPath());
                if (originFile.exists()) {
                    originFile.delete();
                }
            }
            newsService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }
}

package com.gymbuddy.backgymbuddy.admin.youtube.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
import com.gymbuddy.backgymbuddy.admin.youtube.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.YOUTUBE_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class YoutubeController extends BaseController {

    private final String URI_PREFIX = YOUTUBE_PREFIX;

    private final YoutubeService youtubeService;

    /**
     * 전체 유튜브 조회
     */
    @GetMapping(URI_PREFIX + "/all")
    public ResponseEntity<List<Youtube>> selectYoutubeList() {
        return createResponseEntity(true, youtubeService.findAll());
    }

    /**
     * 유튜브 상세조회
     */
    @GetMapping(URI_PREFIX + "detail/{id}")
    public ResponseEntity<Youtube> selectYoutubeDetail(@PathVariable("id") Long id) {
        log.info("유튜브 아이디로 조회: {}", id);
        return createResponseEntity(true, youtubeService.findOne(id));
    }

    /**
     * 유튜브 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertYoutube(@RequestBody Youtube youtube) {
        log.info("유튜브 등록: {}", youtube);
        Long id = youtubeService.save(youtube);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 유튜브 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateYoutube(@PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("유튜브 수정 - id: {}, param: {}", id, param);
        String uploadDate = Objects.toString(param.get("uploadDate"));
        String title = Objects.toString(param.get("title"));
        String contents = Objects.toString(param.get("contents"));
        String link = Objects.toString(param.get("link"));
        youtubeService.update(id, uploadDate, title, contents, link);
        Youtube findYoutube = youtubeService.findOne(id);

        Map<String, Object> result = new HashMap<>();
        result.put("uploadDate", findYoutube.getUploadDate());
        result.put("title", findYoutube.getTitle());
        result.put("contents", findYoutube.getContents());
        result.put("link", findYoutube.getLink());
        return createResponseEntity(true, result);
    }

    /**
     * 유튜브 삭제
     */
    @DeleteMapping(URI_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteYoutube(@RequestParam List<Long> ids) {
        log.info("유튜브 삭제: {}", ids.toString());
        int deleteResult = youtubeService.delete(ids);

        Map<String, Object> result = new HashMap<>();
        result.put("result", deleteResult);
        return createResponseEntity(true, result);
    }
}

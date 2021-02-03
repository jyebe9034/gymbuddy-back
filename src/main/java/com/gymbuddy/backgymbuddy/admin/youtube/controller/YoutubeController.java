package com.gymbuddy.backgymbuddy.admin.youtube.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
import com.gymbuddy.backgymbuddy.admin.youtube.domain.YoutubeDto;
import com.gymbuddy.backgymbuddy.admin.youtube.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @GetMapping(URI_PREFIX + "/all/{page}")
    public ResponseEntity<List<Youtube>> selectYoutubeList(@PathVariable("page") int page) {
        return createResponseEntity(true, youtubeService.findAll(page));
    }

    // TODO 홈화면 쪽에서 어떻게 보여주는지? 최신 9개만 보여주면 되는지 아니면 3개씩 나눠서 콜할건지?

    /**
     * 유튜브 상세
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
    public ResponseEntity<Map<String, Object>> insertYoutube(@ModelAttribute YoutubeDto youtube) {
        log.info("유튜브 등록: {}", youtube);
        // 이미지 업로드
        String rootPath = System.getProperty("user.dir") + "/src/main/resources/static/img/youtube";
        String filename = youtube.getFile().getOriginalFilename();
        try {
            File newFile = new File(rootPath);
            if (!newFile.exists()) {
                newFile.mkdir();
            }
            File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
            youtube.getFile().transferTo(realFile);
            youtube.setImgName(filename);
            youtube.setImgPath("/resources/static/img/youtube/" + realFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        // 저장
        Long id = youtubeService.save(youtube);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 업로드 날짜, 제목, 내용, 링크 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateYoutube(@PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("유튜브 수정 - id: {}, param: {}", id, param);
        youtubeService.update(id, param);
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
    public ResponseEntity<Map<String, Object>> deleteYoutube(@RequestBody List<Integer> ids) {
        log.info("유튜브 삭제: {}", ids.toString());
        youtubeService.delete(ids);

        Map<String, Object> result = new HashMap<>();
        result.put("result", 0);
        return createResponseEntity(true, result);
    }
}

package com.gymbuddy.backgymbuddy.admin.youtube.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
import com.gymbuddy.backgymbuddy.admin.youtube.domain.YoutubeDto;
import com.gymbuddy.backgymbuddy.admin.youtube.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.YOUTUBE_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class YoutubeController extends BaseController {

    private final String URI_PREFIX = YOUTUBE_PREFIX;
    private String youtubePath = "/resources/static/img/youtube";
    private String rootpath = System.getProperty("user.dir") + "/src/main" + youtubePath;
    private File newFile = new File(rootpath);

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
    @GetMapping(URI_PREFIX + "/detail/{id}")
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
        String filename = youtube.getFile().getOriginalFilename();
        try {
            if (!newFile.exists()) {
                newFile.mkdir();
            }
            File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
            youtube.getFile().transferTo(realFile);
            youtube.setImgName(filename);
            youtube.setImgPath(youtubePath + realFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", youtubeService.save(youtube));
        return createResponseEntity(true, result);
    }

    /**
     * 업로드 날짜, 제목, 내용, 링크, 이미지 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateYoutube(@PathVariable("id") Long id, @RequestBody YoutubeDto youtube) {
        log.info("유튜브 수정 - id: {}, youtube: {}", id, youtube);

        Youtube origin = youtubeService.findOne(id);
        // 기존의 이미지 파일과 다른 파일인 경우에만 새로운 파일을 서버에 올린다.
        String filename = youtube.getFile().getOriginalFilename();
        if (!origin.getImgName().equals(filename)) {
            // 이미지 업로드
            try {
                File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
                youtube.getFile().transferTo(realFile);
                youtube.setImgName(filename);
                youtube.setImgPath(youtubePath + realFile.getName());

                // 기존 이미지 파일 서버에서 삭제
                File originFile = new File(newFile + "/" + origin.getImgPath());
                if (originFile.exists()) {
                    originFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        youtubeService.update(id, youtube);
        Youtube findYoutube = youtubeService.findOne(id);

        boolean flag = true;
        // 요청대로 잘 수정된건지 확인한다.
        if (youtube.getUploadDate() != null) {
            flag = youtube.getUploadDate().equals(findYoutube.getUploadDate()) ? true : false;
        }
        if (youtube.getTitle() != null) {
            flag = youtube.getTitle().equals(findYoutube.getTitle()) ? true : false;
        }
        if (youtube.getContents() != null) {
            flag = youtube.getContents().equals(findYoutube.getContents()) ? true : false;
        }
        if (youtube.getLink() != null) {
            flag = youtube.getLink().equals(findYoutube.getLink()) ? true : false;
        }
        if (youtube.getImgPath() != null) {
            flag = youtube.getImgPath().equals(findYoutube.getImgPath()) ? true : false;
        }
        if (youtube.getImgName() != null) {
            flag = youtube.getImgName().equals(findYoutube.getImgName()) ? true : false;
        }
        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 유튜브 삭제
     */
    @DeleteMapping(URI_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteYoutube(@RequestBody List<Integer> ids) {
        log.info("유튜브 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            Youtube origin = youtubeService.findOne(idL);
            // 이미지 삭제
            File originFile = new File(newFile + "/" + origin.getImgPath());
            if (originFile.exists()) {
                originFile.delete();
            }
            youtubeService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }
}

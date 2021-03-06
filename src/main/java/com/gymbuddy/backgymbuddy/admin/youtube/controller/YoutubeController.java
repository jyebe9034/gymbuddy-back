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

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_YOUTUBE_PREFIX;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.YOUTUBE_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class YoutubeController extends BaseController {

    private String youtubePath = "/resources/static/img/youtube";
    private String rootpath = System.getProperty("user.dir") + "/src/main" + youtubePath;
    private File newFile = new File(rootpath);

    private final YoutubeService youtubeService;

    /**
     * 전체 유튜브 갯수 조회
     */
    @GetMapping(YOUTUBE_PREFIX + "/totalCount")
    public ResponseEntity<Map<String, Object>> selectYoutubeTotalCount() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", youtubeService.selectTotalCount());
        return createResponseEntity(true, result);
    }

    /**
     * 전체 유튜브 조회 (관리자 화면 목록, 10개씩)
     */
    @GetMapping(YOUTUBE_PREFIX + "/all/{page}")
    public ResponseEntity<List<Youtube>> selectYoutubeList(@PathVariable("page") int page) {
        return createResponseEntity(true, youtubeService.findAll(page));
    }

    /**
     * 전체 유튜브 조회 (사용자 화면 목록, 15개씩)
     */
    @GetMapping(YOUTUBE_PREFIX + "/allForUser/{page}")
    public ResponseEntity<List<Youtube>> selectYoutubeListForUser(@PathVariable("page") int page) {
        return createResponseEntity(true, youtubeService.findAllForUser(page));
    }

    /**
     * 유튜브 상세
     */
    @GetMapping(YOUTUBE_PREFIX + "/detail/{id}")
    public ResponseEntity<Youtube> selectYoutubeDetail(@PathVariable("id") Long id) {
        log.info("유튜브 아이디로 조회: {}", id);
        return createResponseEntity(true, youtubeService.findOne(id));
    }

    /**
     * 유튜브 등록
     */
    @PostMapping(ADMIN_YOUTUBE_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertYoutube(@ModelAttribute YoutubeDto youtube) {
        log.info("유튜브 등록: {}", youtube);

        // 이미지 업로드
        if (youtube.getFile() != null) {
            String filename = youtube.getFile().getOriginalFilename();
            try {
                if (!newFile.exists()) {
                    newFile.mkdir();
                }
                File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
                youtube.getFile().transferTo(realFile);
                youtube.setImgName(filename);
                youtube.setImgPath(newFile + "/" + realFile.getName());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        return createResponseEntity(true, youtubeService.save(youtube));
    }

    /**
     * 업로드 날짜, 제목, 내용, 링크, 이미지 수정
     */
    @PutMapping(ADMIN_YOUTUBE_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateYoutube(@PathVariable("id") Long id, @ModelAttribute YoutubeDto youtube) {
        log.info("유튜브 수정 - id: {}, youtube: {}", id, youtube);

        // 이미지가 있는 경우에만 실행
        if (youtube.getFile() != null) {
            // 기존의 이미지 파일과 다른 파일인 경우에만 새로운 파일을 서버에 올린다.
            String filename = youtube.getFile().getOriginalFilename();
            // 이미지 업로드
            try {
                File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
                youtube.getFile().transferTo(realFile);
                youtube.setImgName(filename);
                youtube.setImgPath(newFile + "/" + realFile.getName());

                // 기존 이미지 파일 서버에서 삭제
                Youtube origin = youtubeService.findOne(id);

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
    @DeleteMapping(ADMIN_YOUTUBE_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteYoutube(@RequestBody List<Integer> ids) {
        log.info("유튜브 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            Youtube origin = youtubeService.findOne(idL);
            // 이미지 삭제
            if (origin.getImgPath() != null) {
                File originFile = new File(origin.getImgPath());
                if (originFile.exists()) {
                    originFile.delete();
                }
            }
            youtubeService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }
}

package com.gymbuddy.backgymbuddy.admin.youtube.controller;

import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
import com.gymbuddy.backgymbuddy.admin.youtube.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class YoutubeController {

    private final YoutubeService youtubeService;

    /**
     * 전체 유튜브 조회
     */
    @GetMapping("/allYoutube")
    public List<Youtube> selectYoutubeList() {
        return null;
    }

    /**
     * 유튜브 상세조회
     */
    @GetMapping("youtubeDetail/{no}")
    public Map<String, Object> selectYoutubeDetail(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 유튜브 등록
     */
    @PostMapping("/newYoutube")
    public Map<String, Object> insertYoutube(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 유튜브 수정
     */
    @PostMapping("/updateYoutube")
    public Map<String, Object> updateYoutube(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 유튜브 삭제
     */
    @GetMapping("/deleteYoutube")
    public Map<String, Object> deleteYoutube(@RequestParam Map<String, Object> param) {
        return null;
    }
}

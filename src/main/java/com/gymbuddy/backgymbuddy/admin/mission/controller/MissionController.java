package com.gymbuddy.backgymbuddy.admin.mission.controller;

import com.gymbuddy.backgymbuddy.admin.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    /**
     * 미션과 비전 조회
     */
    @GetMapping("/mission")
    public Map<String, Object> selectMission() {
        return null;
    }

    /**
     * 미션 등록
     */
    @PostMapping("/newMission")
    public Map<String, Object> insertMission(@RequestBody Map<String, Object> praram) {
        return null;
    }

    /**
     * 미션 수정
     */
    @PostMapping("/updateMission")
    public Map<String, Object> updateMission(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 미션 삭제
     * @param no 삭제할 글번호
     */
    @GetMapping("/deleteMission/{no}")
    public Map<String, Object> deleteMission(@PathVariable("no") int no) {
        return null;
    }
}

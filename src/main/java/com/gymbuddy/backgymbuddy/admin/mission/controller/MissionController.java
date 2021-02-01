package com.gymbuddy.backgymbuddy.admin.mission.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import com.gymbuddy.backgymbuddy.admin.mission.domain.Mission;
import com.gymbuddy.backgymbuddy.admin.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.MISSION_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MissionController extends BaseController {

    private final String URI_PREFIX = MISSION_PREFIX;
    private final MissionService missionService;

    /**
     * 미션과 비전 조회
     */
    @GetMapping(URI_PREFIX + "/mission")
    public ResponseEntity<Map<String, Object>> selectMission(Long id) {
        return createResponseEntity(true, missionService.find(id));
    }

    /**
     * 미션 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertMission(@RequestBody Mission mission) {
        log.info("공지사항 등록: {}", mission);
        Long id = missionService.save(mission);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 미션 수정
     */
    @PostMapping(URI_PREFIX + "/update")
    public ResponseEntity<Map<String, Object>> updateMission(
            @PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("미션 수정 id: {}, param: {}", id, param);
        missionService.update(id, param);

        Mission findMission = missionService.find(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("contents", findMission.getContents());
        return createResponseEntity(true, result);
    }

}

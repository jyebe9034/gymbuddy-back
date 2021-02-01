package com.gymbuddy.backgymbuddy.admin.history.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import com.gymbuddy.backgymbuddy.admin.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.HISTORY_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HistoryController extends BaseController {

    private final String URI_PREFIX = HISTORY_PREFIX;

    private final HistoryService historyService;

    /**
     * 전체 활동기록 조회 (관리자)
     */
    @GetMapping(URI_PREFIX + "/allToAdmin")
    public ResponseEntity<List<History>> selectHistoryList() {
        return createResponseEntity(true, historyService.findALl());
    }

    /**
     * 전체 활동기록 조회 (사용자) -> 시간별 정렬
     */
    @GetMapping(URI_PREFIX + "/allToUser")
    public ResponseEntity<List<History>> selectHistoryListByDate() {
        return createResponseEntity(true, historyService.findAllByDate());
    }

    /**
     * 활동기록 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertHistory(@RequestBody History history) {
        log.info("활동기록 등록: {}", history);
        Long id = historyService.save(history);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 활동기록 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateHistory(
            @PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("활동기록 수정 id: {}, param: {}", id, param);
        historyService.update(id, param);

        History findHistory = historyService.findOne(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("historyDate", findHistory.getHistoryDate());
        result.put("title", findHistory.getTitle());
        return createResponseEntity(true, result);
    }

    /**
     * 활동기록 삭제
     * @param id 삭제할 글번호
     */
    @DeleteMapping(URI_PREFIX + "/delete/{id}")
    public ResponseEntity<History> deleteHistory(@PathVariable("id") Long id) {
        log.info("활동기록 삭제: {}", id);
        return createResponseEntity(true, historyService.delete(id));
    }

}

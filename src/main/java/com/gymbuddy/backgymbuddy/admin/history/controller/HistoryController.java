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
     * 전체 활동기록 조회
     */
    @GetMapping(URI_PREFIX + "/allHistory")
    public ResponseEntity<List<History>> selectHistoryList() {
        return createResponseEntity(true, historyService.findALl());
    }

    /**
     * 활동기록 등록
     */
    @PostMapping(URI_PREFIX + "/newHistory")
    public ResponseEntity<Map<String, Object>> insertHistory(@RequestBody History history) {
        log.info("활동기록 등록: {}", history);
        Long id = historyService.save(history);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("historyDate", history.getHistoryDate());
        result.put("title", history.getTitle());
        return createResponseEntity(true, result);
    }

    /**
     * 활동기록 수정
     */
    @PutMapping(URI_PREFIX + "/updateHistory/{id}")
    public ResponseEntity<Map<String, Object>> updateHistory(
            @PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("활동기록 등록 id: {}, param: {}", id, param);
        historyService.update(id);

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
    @DeleteMapping(URI_PREFIX + "/deleteHistory/{id}")
    public ResponseEntity<History> deleteHistory(@PathVariable("id") Long id) {
        log.info("활동기록 삭제: {}", id);
        return createResponseEntity(true, historyService.delete(id));
    }

}

package com.gymbuddy.backgymbuddy.admin.history.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import com.gymbuddy.backgymbuddy.admin.history.domain.HistoryDto;
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
     * 활동기록 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertHistory(@RequestBody HistoryDto dto) {
        log.info("활동기록 등록: {}", dto);

        Map<String, Object> result = new HashMap<>();
        result.put("id", historyService.save(dto));
        return createResponseEntity(true, result);
    }

    /**
     * 활동기록 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateHistory(
            @PathVariable("id") Long id, @RequestBody HistoryDto dto) {
        log.info("활동기록 수정 id: {}, dto: {}", id, dto);

        historyService.update(id, dto);
        History findHistory = historyService.findOne(id);

        boolean flag = true;
        if (dto.getHistoryDate() != null) {
            flag = dto.getHistoryDate().equals(findHistory.getHistoryDate());
        }
        if (dto.getTitle() != null) {
            flag = dto.getTitle().equals(findHistory.getTitle()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 활동기록 삭제
     */
    @DeleteMapping(URI_PREFIX + "/delete/{id}")
    public ResponseEntity<History> deleteHistory(@PathVariable("id") Long id) {
        log.info("활동기록 삭제: {}", id);
        historyService.delete(id);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

}

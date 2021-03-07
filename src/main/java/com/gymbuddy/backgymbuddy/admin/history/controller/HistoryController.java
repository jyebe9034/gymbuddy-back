package com.gymbuddy.backgymbuddy.admin.history.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.history.domain.HistoryDto;
import com.gymbuddy.backgymbuddy.admin.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_HISTORY_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HistoryController extends BaseController {

    private final HistoryService historyService;

    /**
     * 활동기록 등록
     */
    @PostMapping(ADMIN_HISTORY_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertHistory(@RequestBody HistoryDto dto) {
        log.info("활동기록 등록: {}", dto);

        Map<String, Object> result = new HashMap<>();
        result.put("id", historyService.save(dto));
        return createResponseEntity(true, result);
    }

    /**
     * 활동기록 수정
     */
    @PutMapping(ADMIN_HISTORY_PREFIX + "/update")
    public ResponseEntity<Map<String, Object>> updateHistory(@RequestBody List<HistoryDto> dtoList) {
        log.info("활동기록 수정 dtoList: {}", dtoList);

        historyService.update(dtoList);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

}

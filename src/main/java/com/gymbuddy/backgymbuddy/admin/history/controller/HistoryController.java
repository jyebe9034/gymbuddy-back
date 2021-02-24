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

        for (HistoryDto dto : dtoList) {
            Long id = dto.getId();
            historyService.update(id, dto);
        }

        boolean flag = true;
        for (HistoryDto dto : dtoList) {
            Long id = dto.getId();
            History findHistory = historyService.findOne(id);
            if (dto.getHistoryDate() != null) {
                flag = dto.getHistoryDate().equals(findHistory.getHistoryDate()) ? true : false;
            }
            if (dto.getTitle() != null) {
                flag = dto.getTitle().equals(findHistory.getTitle()) ? true : false;
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 활동기록 삭제
     */
    @DeleteMapping(ADMIN_HISTORY_PREFIX + "/delete")
    public ResponseEntity<History> deleteHistory(@RequestBody List<Integer> ids) {
        log.info("활동기록 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            historyService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

}

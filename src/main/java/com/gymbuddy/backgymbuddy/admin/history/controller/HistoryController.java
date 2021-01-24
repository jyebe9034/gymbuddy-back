package com.gymbuddy.backgymbuddy.admin.history.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import com.gymbuddy.backgymbuddy.admin.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HistoryController extends BaseController {

    private final HistoryService historyService;

    /**
     * 전체 활동기록 조회
     */
    @GetMapping("/allHistory")
    public List<History> selectHistoryList() {
        return null;
    }

    /**
     * 활동기록 등록
     */
    @PostMapping("/newHistory")
    public Map<String, Object> insertHistory(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 활동기록 삭제
     * @param no 삭제할 글번호
     */
    @GetMapping("/deleteHistory/{no}")
    public Map<String, Object> deleteHistory(@PathVariable("no") int no) {
        return null;
    }

}

package com.gymbuddy.backgymbuddy.admin.column.controller;

import com.gymbuddy.backgymbuddy.admin.column.domain.Column;
import com.gymbuddy.backgymbuddy.admin.column.service.ColumnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    /**
     * 전체 칼럼 조회
     */
    @GetMapping("/allColumn")
    public List<Column> selectColumnList() {
        return null;
    }

    /**
     * 칼럼 상세
     */
    @GetMapping("/columnDetail/{no}")
    public Map<String, Object> selectColumnDetail(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 칼럼 등록
     */
    @PostMapping("/newColumn")
    public Map<String, Object> insertNewColumn(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 칼럼 수정
     */
    @PostMapping("/updateColumn")
    public Map<String, Object> updateColumn(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 칼럼 삭제
     */
    @GetMapping("/deleteColumn")
    public Map<String, Object> deleteColumn(@RequestParam Map<String, Object> param) {
        return null;
    }
}

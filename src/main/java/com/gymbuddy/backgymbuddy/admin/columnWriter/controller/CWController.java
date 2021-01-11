package com.gymbuddy.backgymbuddy.admin.columnWriter.controller;

import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import com.gymbuddy.backgymbuddy.admin.columnWriter.service.CWService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CWController {

    private final CWService CWService;

    /**
     * 전체 칼럼 작성자 조회
     */
    @GetMapping("/allColumnWriter")
    public List<ColumnWriter> selectColumnWriterList() {
        return null;
    }

    /**
     * 칼럼 작성자 상세
     */
    @GetMapping("/columnWriterDetail/{no}")
    public Map<String, Object> selectColumnWriterDetail(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 칼럼 작성자 등록
     */
    @PostMapping("/newColumnWriter")
    public Map<String, Object> insertColumnWriter(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 칼럼 작성자 수정
     */
    @PostMapping("/updateColumnWriter")
    public Map<String, Object> updateColumnWriter(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 칼럼 작성자 삭제
     */
    @GetMapping("/deleteColumnWriter")
    public Map<String, Object> deleteColumnWriter(@RequestParam Map<String, Object> param) {
        return null;
    }
}

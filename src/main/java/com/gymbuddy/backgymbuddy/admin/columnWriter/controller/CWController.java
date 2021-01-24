package com.gymbuddy.backgymbuddy.admin.columnWriter.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import com.gymbuddy.backgymbuddy.admin.columnWriter.service.CWService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.COLUMN_WRITER_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CWController extends BaseController {

    private final String URI_PREFIX = COLUMN_WRITER_PREFIX;

    private final CWService CWService;

    /**
     * 전체 칼럼 작성자 조회
     */
    @GetMapping(URI_PREFIX + "/all")
    public ResponseEntity<List<ColumnWriter>> selectColumnWriterList() {
        List<ColumnWriter> list = CWService.selectColumnWriterList();
        return createResponseEntity(true, list);
    }

    /**
     * 칼럼 작성자 상세
     */
    @GetMapping(URI_PREFIX + "/detail/{columnWriterId}")
    public ResponseEntity<ColumnWriter> selectColumnWriterDetail(@PathVariable("columnWriterId") Long columnWriterId) {
        ColumnWriter result = CWService.selectColumnWriterDetail(columnWriterId);
        return createResponseEntity(true, result);
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

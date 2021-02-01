package com.gymbuddy.backgymbuddy.admin.columnWriter.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import com.gymbuddy.backgymbuddy.admin.columnWriter.service.CWService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.COLUMN_WRITER_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CWController extends BaseController {

    private final String URI_PREFIX = COLUMN_WRITER_PREFIX;

    private final CWService cwService;

    /**
     * 전체 칼럼 작성자 조회
     */
    @GetMapping(URI_PREFIX + "/all/{page}")
    public ResponseEntity<List<ColumnWriter>> selectColumnWriterList(@PathVariable("page") int page) {
        return createResponseEntity(true, cwService.findAll(page));
    }

    /**
     * 칼럼 작성자 상세
     */
    @GetMapping(URI_PREFIX + "/detail/{id}")
    public ResponseEntity<ColumnWriter> selectColumnWriterDetail(@PathVariable("id") Long id) {
        log.info("칼럼 작성자 아이디로 조회: {}", id);
        return createResponseEntity(true, cwService.findOne(id));
    }

    /**
     * 칼럼 작성자 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertColumnWriter(@RequestBody ColumnWriter columnWriter) {
        log.info("컬럼 작성자 등록: {}", columnWriter);
        Long id = cwService.save(columnWriter);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 칼럼 작성자 내용 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateColumnWriter(@PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("컬럼 작성자 수정 - id: {}, param: {}", id, param);
        cwService.update(id, param);
        ColumnWriter findColumnWriter = cwService.findOne(id);

        Map<String, Object> result = new HashMap<>();
        result.put("id", findColumnWriter.getId());
        result.put("contents", findColumnWriter.getContents());
        return createResponseEntity(true, result);
    }

    /**
     * 칼럼 작성자 삭제
     */
    @DeleteMapping(URI_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteColumnWriter(@RequestBody List<Integer> ids) {
        log.info("컬럼 작성자 삭제 : {}", ids);
        cwService.delete(ids);

        Map<String, Object> result = new HashMap<>();
        result.put("result", 0);
        return createResponseEntity(true, result);
    }

}

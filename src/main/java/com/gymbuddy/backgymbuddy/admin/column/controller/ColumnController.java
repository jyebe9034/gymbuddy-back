package com.gymbuddy.backgymbuddy.admin.column.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import com.gymbuddy.backgymbuddy.admin.column.service.ColumnService;
import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.COLUMN_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ColumnController extends BaseController {

    private final String URI_PREFIX = COLUMN_PREFIX;

    private final ColumnService columnService;

    /**
     * 전체 칼럼 조회
     */
    @GetMapping(URI_PREFIX + "/all/{page}")
    public ResponseEntity<List<Columns>> selectColumnList(@PathVariable("page") int page) {
        return createResponseEntity(true, columnService.findAll(page));
    }

    /**
     * 칼럼 상세
     */
    @GetMapping(URI_PREFIX + "/detail/{id}")
    public ResponseEntity<Columns> selectColumnDetail(@PathVariable("id") Long id) {
        log.info("컬럼 아이디로 조회: {}", id);
        return createResponseEntity(true, columnService.findOne(id));
    }

    /**
     * 칼럼 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertNewColumn(@RequestBody Columns columns) {
        log.info("컬럼 등록: {}", columns);
        Long id = columnService.save(columns);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 칼럼 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateColumn(@PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("컬럼 수정 - id: {}, param: {}", id, param);
        columnService.update(id, param);
        Columns findColumn = columnService.findOne(id);

        Map<String, Object> result = new HashMap<>();
        result.put("title", findColumn.getTitle());
        result.put("contents", findColumn.getContents());
        result.put("columnWriter", findColumn.getColumnWriter());
        return createResponseEntity(true, result);
    }

    /**
     * 칼럼 삭제
     */
    @DeleteMapping(URI_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteColumn(@RequestBody List<Integer> ids) {
        log.info("컬럼 삭제: {}", ids);
        columnService.delete(ids);

        Map<String, Object> result = new HashMap<>();
        result.put("result", 0);
        return createResponseEntity(true, result);
    }
}

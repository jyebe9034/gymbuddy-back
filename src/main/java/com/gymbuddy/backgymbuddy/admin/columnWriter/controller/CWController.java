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

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_COLUMN_WRITER_PREFIX;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.COLUMN_WRITER_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CWController extends BaseController {

    private final CWService cwService;

    /**
     * 전체 칼럼 작성자 갯수 조회
     */
    @GetMapping(COLUMN_WRITER_PREFIX + "/totalCount")
    public ResponseEntity<Map<String, Object>> selectColumnWriterTotalCount() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", cwService.selectTotalCount());
        return createResponseEntity(true, result);
    }

    /**
     * 전체 칼럼 작성자 조회
     */
    @GetMapping(COLUMN_WRITER_PREFIX + "/all/{page}")
    public ResponseEntity<List<ColumnWriter>> selectColumnWriterList(@PathVariable("page") int page) {
        return createResponseEntity(true, cwService.findAll(page));
    }

    /**
     * 칼럼 작성자 상세
     */
    @GetMapping(COLUMN_WRITER_PREFIX + "/detail/{id}")
    public ResponseEntity<ColumnWriter> selectColumnWriterDetail(@PathVariable("id") Long id) {
        log.info("칼럼 작성자 아이디로 조회: {}", id);
        return createResponseEntity(true, cwService.findOne(id));
    }

    /**
     * 칼럼 작성자 등록
     */
    @PostMapping(ADMIN_COLUMN_WRITER_PREFIX + "/new")
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
    @PutMapping(ADMIN_COLUMN_WRITER_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateColumnWriter(@PathVariable("id") Long id, @RequestBody ColumnWriter columnWriter) {
        log.info("컬럼 작성자 수정 - id: {}, columnWriter: {}", id, columnWriter);
        cwService.update(id, columnWriter);
        ColumnWriter findColumnWriter = cwService.findOne(id);

        boolean flag = true;
        if (columnWriter.getName() != null) {
            flag = columnWriter.getName().equals(findColumnWriter.getName()) ? true : false;
        }
        if (columnWriter.getJob() != null) {
            flag = columnWriter.getJob().equals(findColumnWriter.getJob()) ? true : false;
        }
        if (columnWriter.getContents() != null) {
            flag = columnWriter.getContents().equals(findColumnWriter.getContents()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 칼럼 작성자 삭제
     */
    @DeleteMapping(ADMIN_COLUMN_WRITER_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteColumnWriter(@RequestBody List<Integer> ids) {
        log.info("컬럼 작성자 삭제 : {}", ids);
        cwService.delete(ids);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

}

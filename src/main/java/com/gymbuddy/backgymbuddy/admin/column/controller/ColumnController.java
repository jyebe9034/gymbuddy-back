package com.gymbuddy.backgymbuddy.admin.column.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import com.gymbuddy.backgymbuddy.admin.column.domain.ColumnsDto;
import com.gymbuddy.backgymbuddy.admin.column.service.ColumnService;
import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.COLUMN_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ColumnController extends BaseController {

    private String columnPath = "/resources/static/img/columns";
    private String rootPath = System.getProperty("user.dir") + "/src/main" + columnPath;
    private File newfile = new File(rootPath);

    private final ColumnService columnService;

    /**
     * 전체 칼럼 조회(관리자)
     */
    @GetMapping(COLUMN_PREFIX + "/all/{page}")
    public ResponseEntity<List<Columns>> selectColumnList(@PathVariable("page") int page) {
        return createResponseEntity(true, columnService.findAll(page));
    }

    /**
     * 칼럼 상세
     */
    @GetMapping(COLUMN_PREFIX + "/detail/{id}")
    public ResponseEntity<Columns> selectColumnDetail(@PathVariable("id") Long id) {
        log.info("컬럼 아이디로 조회: {}", id);
        return createResponseEntity(true, columnService.findOne(id));
    }

    /**
     * 칼럼 등록
     */
    @PostMapping(COLUMN_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertColumn(@ModelAttribute ColumnsDto columns) {
        log.info("컬럼 등록: {}", columns);

        // 이미지 업로드
        String filename = columns.getFile().getOriginalFilename();
        try {
            if (!newfile.exists()) {
                newfile.mkdir();
            }
            File realFile = new File(newfile + "/" + System.currentTimeMillis() + "_" + filename);
            columns.getFile().transferTo(realFile);
            columns.setImgName(realFile.getName());
            columns.setImgPath(columnPath + "/" + realFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", columnService.save(columns));
        return createResponseEntity(true, result);
    }

    /**
     * 칼럼 수정
     */
    @PutMapping(COLUMN_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateColumn(@PathVariable("id") Long id, @ModelAttribute ColumnsDto columns) {
        log.info("컬럼 수정 - id: {}, columns: {}", id, columns);

        if (columns.getFile() != null) {
            Columns origin = columnService.findOne(id);
            String filename = columns.getFile().getOriginalFilename();
            if (!origin.getImgName().equals(filename)) {
                // 이미지 업로드
                try {
                    File realFile = new File(newfile + "/" + System.currentTimeMillis() + "_" + filename);
                    columns.getFile().transferTo(realFile);
                    columns.setImgName(filename);
                    columns.setImgPath(columnPath + "/" + realFile.getName());

                    // 기존 이미지 파일 서버에서 삭제
                    File originFile = new File(newfile + "/" + origin.getImgPath());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }

        columnService.update(id, columns);
        Columns findColumn = columnService.findOne(id);

        boolean flag = true;
        if (columns.getTitle() != null) {
            flag = columns.getTitle().equals(findColumn.getTitle()) ? true : false;
        }
        if (columns.getContents() != null) {
            flag = columns.getContents().equals(findColumn.getContents()) ? true : false;
        }
        if (columns.getImgPath() != null) {
            flag = columns.getImgPath().equals(findColumn.getImgPath()) ? true : false;
        }
        if (columns.getImgName() != null) {
            flag = columns.getImgName().equals(findColumn.getImgName()) ? true : false;
        }
        if (columns.getMainYn() != null) {
            flag = columns.getMainYn().equals(findColumn.getMainYn()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 칼럼 삭제
     */
    @DeleteMapping(COLUMN_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteColumn(@RequestBody List<Integer> ids) {
        log.info("컬럼 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            Columns origin = columnService.findOne(idL);
            // 이미지 삭제
            File originFile = new File(newfile + "/" + origin.getImgPath());
            if (originFile.exists()) {
                originFile.delete();
            }
            columnService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }
}

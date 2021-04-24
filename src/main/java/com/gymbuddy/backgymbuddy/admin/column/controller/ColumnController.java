package com.gymbuddy.backgymbuddy.admin.column.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.base.DirMake;
import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import com.gymbuddy.backgymbuddy.admin.column.domain.ColumnsDto;
import com.gymbuddy.backgymbuddy.admin.column.service.ColumnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_COLUMN_PREFIX;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.COLUMN_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ColumnController extends BaseController {

    private String columnPath = "/resources/images/columns";
    private String rootPath = "/home/www" + columnPath;
    private File newFile = DirMake.testdir(rootPath);

    private final ColumnService columnService;

    /**
     * 전체 컬럼 갯수 조회
     */
    @GetMapping(COLUMN_PREFIX + "/totalCount")
    public ResponseEntity<Map<String, Object>> selectColumnTotalCount() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", columnService.selectTotalCount());
        return createResponseEntity(true, result);
    }

    /**
     * 전체 칼럼 조회 (관리자 화면 목록, 10개씩)
     */
    @GetMapping(COLUMN_PREFIX + "/all/{page}")
    public ResponseEntity<List<Columns>> selectColumnList(@PathVariable("page") int page) {
        return createResponseEntity(true, columnService.findAll(page));
    }

    /**
     * 전체 컬럼 조회 (사용자 화면 목록, 15개씩)
     * @param page
     * @return
     */
    @GetMapping(COLUMN_PREFIX + "/allForUser/{page}")
    public ResponseEntity<List<Columns>> selectColumnListForUser(@PathVariable("page") int page) {
        return createResponseEntity(true, columnService.findAllForUser(page));
    }

    /**
     * 칼럼 상세
     */
    @GetMapping(COLUMN_PREFIX + "/detail/{id}")
    public ResponseEntity<Columns> selectColumnDetail(@PathVariable("id") Long id) {
        log.info("컬럼 아이디로 조회: {}", id);
        return createResponseEntity(true, columnService.findOneDto(id));
    }

    /**
     * 칼럼 등록
     */
    @PostMapping(ADMIN_COLUMN_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertColumn(@ModelAttribute ColumnsDto columns) {
        log.info("컬럼 등록: {}", columns);

        // 이미지 업로드
        String filename = columns.getFile().getOriginalFilename();
        try {
            if (!newFile.exists()) {
                try {
                    newFile.mkdir();
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
            File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
            columns.getFile().transferTo(realFile);
            columns.setImgName(realFile.getName());
            columns.setImgPath(columnPath + "/" + realFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return createResponseEntity(true, columnService.save(columns));
    }

    /**
     * 칼럼 수정
     */
    @PutMapping(ADMIN_COLUMN_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateColumn(@PathVariable("id") Long id, @ModelAttribute ColumnsDto columns) {
        log.info("컬럼 수정 - id: {}, columns: {}", id, columns);

        if (columns.getFile() != null) {
            String filename = columns.getFile().getOriginalFilename();
            // 이미지 업로드
            try {
                File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
                columns.getFile().transferTo(realFile);
                columns.setImgName(realFile.getName());
                columns.setImgPath(columnPath + "/" + realFile.getName());

                // 기존 이미지 파일 서버에서 삭제
                Columns origin = columnService.findOne(id);
                if (origin.getImgPath() != null) {
                    File originFile = new File(origin.getImgPath());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
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

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 칼럼 삭제
     */
    @DeleteMapping(ADMIN_COLUMN_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteColumn(@RequestBody List<Integer> ids) {
        log.info("컬럼 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            Columns origin = columnService.findOne(idL);
            // 이미지 삭제
            if (origin.getImgPath() != null) {
                File originFile = new File(origin.getImgPath());
                if (originFile.exists()) {
                    originFile.delete();
                }
            }
            columnService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }
}

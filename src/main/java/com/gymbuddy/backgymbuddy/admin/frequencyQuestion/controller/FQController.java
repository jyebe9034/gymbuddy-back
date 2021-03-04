package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FQDto;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FrequencyQuestion;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.service.FQService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FQController extends BaseController {

    private final FQService fqService;

    /**
     * 전체 자주묻는질문 조회 (관리자)
     */
    @GetMapping(ADMIN_FQ_PREFIX + "/all/{page}")
    public ResponseEntity<List<FrequencyQuestion>> selectAdminFqList(@PathVariable("page") int page) {
        return createResponseEntity(true, fqService.findAll(page));
    }

    /**
     * 전체 자주묻는질문 조회 (사용자)
     */
    @GetMapping(FQ_PREFIX + "/all")
    public ResponseEntity<List<FrequencyQuestion>> selectAllFqList() {
        return createResponseEntity(true, fqService.findAllByCategoryId());
    }

    /**
     * 전체 굿즈 갯수 조회
     */
    @GetMapping(FQ_PREFIX + "/totalCount")
    public ResponseEntity<Map<String, Object>> selectFaqTotalCount() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", fqService.selectTotalCount());
        return createResponseEntity(true, result);
    }

    /**
     * 자주묻는질문 상세
     */
    @GetMapping(FQ_PREFIX + "/detail/{id}")
    public ResponseEntity<FrequencyQuestion> selectFqDetail(@PathVariable("id") Long id) {
        log.info("자주묻는질문 조회: {}", id);
        return createResponseEntity(true, fqService.findOne(id));
    }

    /**
     * 자주묻는질문 등록
     */
    @PostMapping(FQ_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertFq(@RequestBody FQDto dto) {
        log.info("자주묻는질문 등록: {}", dto);

        Map<String, Object> result = new HashMap<>();
        result.put("id", fqService.save(dto));
        return createResponseEntity(true, result);
    }

    /**
     * 자주묻는질문 수정
     */
    @PutMapping(ADMIN_FQ_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateFq(
            @PathVariable("id") Long id, @RequestBody FQDto dto) {
        log.info("자주묻는질문 수정 - id: {}, dto: {}", id, dto);

        fqService.update(id, dto);
        FrequencyQuestion findFq = fqService.findOne(id);

        boolean flag = true;
        if (dto.getCategoryId() != null) {
            flag = dto.getCategoryId().equals(findFq.getCategoryId()) ? true : false;
        }
        if (dto.getTitle() != null) {
            flag = dto.getTitle().equals(findFq.getTitle()) ? true : false;
        }
        if (dto.getContents() != null) {
            flag = dto.getContents().equals(findFq.getContents()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 자주묻는질문 삭제
     */
    @DeleteMapping(ADMIN_FQ_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteFq(@RequestBody List<Integer> ids) {
        log.info("자주묻는질문 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            fqService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }
}

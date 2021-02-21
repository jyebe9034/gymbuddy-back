package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FQDto;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FrequencyQuestion;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.service.FQService;
import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.FQ_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FQController extends BaseController {

    private final String URI_PREFIX = FQ_PREFIX;

    private final FQService fqService;

    /**
     * 전체 자주묻는질문 조회
     */
    @GetMapping(URI_PREFIX + "/all/{page}")
    public ResponseEntity<List<FrequencyQuestion>> selectFqList(@PathVariable("page") int page) {
        return createResponseEntity(true, fqService.findAll(page));
    }

    /**
     * 자주묻는질문 상세
     */
    @GetMapping(URI_PREFIX + "/detail/{id}")
    public ResponseEntity<FrequencyQuestion> selectFqDetail(@PathVariable("id") Long id) {
        log.info("자주묻는질문 조회: {}", id);
        return createResponseEntity(true, fqService.findOne(id));
    }

    /**
     * 자주묻는질문 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertFq(@ModelAttribute FQDto dto) {
        log.info("자주묻는질문 등록: {}", dto);

        Map<String, Object> result = new HashMap<>();
        result.put("id", fqService.save(dto));
        return createResponseEntity(true, result);
    }

    /**
     * 자주묻는질문 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateFq(
            @PathVariable("id") Long id, @ModelAttribute FQDto dto) {
        log.info("자주묻는질문 수정 - id: {}, dto: {}", id, dto);

        fqService.update(id, dto);
        FrequencyQuestion findFq = fqService.findOne(id);

        boolean flag = true;
        if (dto.getCategoryId() != null) {
            flag = dto.getCategoryId().equals(findFq.getCategoryId());
        }
        if (dto.getTitle() != null) {
            flag = dto.getTitle().equals(findFq.getTitle());
        }
        if (dto.getContents() != null) {
            flag = dto.getContents().equals(findFq.getContents());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 자주묻는질문 삭제
     */
    @GetMapping(URI_PREFIX + "/delete")
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
